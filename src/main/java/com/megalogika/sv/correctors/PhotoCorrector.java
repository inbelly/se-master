/**
 * 
 */
package com.megalogika.sv.correctors;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;

import com.megalogika.sv.model.Photo;

/**
 * Corrects photos (product ingredients and label) from java serialized form to DB structure.
 * 
 * @author rodu
 *
 */
public class PhotoCorrector {

    public static final String JDBC_URL = "jdbc:postgresql://localhost:25432/se_inbelly";
    public static final String JDBC_USERNAME = "se_inbelly";
    public static final String JDBC_PASSWD = "nenervink173";
    
    
    protected static Connection conn;
    
    protected static PreparedStatement insertPhoto;
    
    protected static PreparedStatement updateProduct;
    
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
         conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWD);
         conn.setAutoCommit(false);
         System.out.println("Connected to " + JDBC_URL);
         
         ResultSet prodOldsRs = conn.createStatement().executeQuery("SELECT id, ingredients, label FROM product");

         insertPhoto = conn.prepareStatement("INSERT INTO photo(id,  height, originalphoto,  photo, resizedphoto, width)" +
                 "VALUES (?, ?, ?, ?, ?, ?)");
         
         updateProduct = conn.prepareStatement("UPDATE product_new set ingredients = ?, label = ? WHERE id = ?");
         
         int cnt = 0;
         while (prodOldsRs.next()) {
             long ingredientId = -1;
             long labelId = -1;
             byte[] data = prodOldsRs.getBytes(2);
             if (null != data) {
                 ingredientId = cnt * 2;
                 Photo ingredient = restorePhotoObject(data);
                 insertPhoto(ingredient, ingredientId);
                 //System.out.println(" Photo: " + ingredient.getId() + "for product[" + prodOldsRs.getLong(1) + "] -> " + ingredient.getPhoto());
             }
             data = prodOldsRs.getBytes(3);
             if (null != data) {
                 labelId = cnt * 2 + 1;
                 Photo label = restorePhotoObject(data);
                 insertPhoto(label, labelId);
                 //System.out.println(" Photo: " + ingredient.getId() + "for product[" + prodOldsRs.getLong(1) + "] -> " + ingredient.getPhoto());
             }
             
             
             updateProduct.setLong(3, prodOldsRs.getLong(1));
             if (ingredientId >= 0) 
                 updateProduct.setLong(1, ingredientId);
             else
                 updateProduct.setNull(1, Types.DOUBLE);
             
             if (labelId >= 0) 
                 updateProduct.setLong(2, labelId);
             else
                 updateProduct.setNull(2, Types.DOUBLE);
             
             int upCnt = updateProduct.executeUpdate();
             if (upCnt != 1)
                 throw new RuntimeException("Update resulted in <> 1 updated rows");
             
             conn.commit();
             
             cnt++;
         }
         System.out.println("Processed " + cnt + " records.");
         insertPhoto.close();
         updateProduct.close();
         prodOldsRs.close();
         conn.close();
    }
    
    protected static Photo restorePhotoObject(byte[] data) throws Exception {
        //data = ArrayUtils.remove(data, 0);
        //data = Base64.decodeBase64(data);
        //System.out.println("   Data:" + new String(data));
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Photo p = (Photo)ois.readObject();
        ois.close();
        return p;
    }
    
    protected static void insertPhoto(Photo photo, long id) throws Exception {
        //= conn.prepareStatement("INSERT INTO photo(id,  height, originalphoto,  photo, resizedphoto, width)" +
        //                              "VALUES (?, ?, ?, ?, ?, ?)");
        insertPhoto.setLong(1, id);
        insertPhoto.setInt(2, photo.getHeight());
        insertPhoto.setString(3, photo.getOriginalPhoto());
        insertPhoto.setString(4, photo.getPhoto());
        insertPhoto.setString(5, photo.getResizedPhoto());
        insertPhoto.setInt(6, photo.getWidth());
        insertPhoto.executeUpdate();
    }

}
