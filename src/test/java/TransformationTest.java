import com.megalogika.sv.model.Transformation;

import junit.framework.TestCase;


public class TransformationTest extends TestCase {
	
	public void testTransformation(){
		Transformation t = new Transformation();
		
		assertEquals(0.0, t.getRotation());
		
		t.rotate(90);
		assertEquals(90.0, t.getRotation());
		
		t.rotate(90);
		assertEquals(180.0, t.getRotation());
		
		t.rotate(90);
		assertEquals(270.0, t.getRotation());
		
		t.rotate(90);
		assertEquals(0.0, t.getRotation());
		
		t.rotate(364);
		assertEquals(4.0, t.getRotation());
		
		t.rotate(0);
		assertEquals(4.0, t.getRotation());
		
		t.rotate(-4);
		assertEquals(0.0, t.getRotation());
		
		t.rotate(-90);
		assertEquals(270.0, t.getRotation());
		
		t.rotate(-90);
		assertEquals(180.0, t.getRotation());
		
		t.rotate(-90);
		assertEquals(90.0, t.getRotation());
		
		t.rotate(-90);
		assertEquals(0.0, t.getRotation());
		
		t.rotate(-364);
		assertEquals(356.0, t.getRotation());
		
		t.rotate(4);
		assertEquals(0.0, t.getRotation());
		
		t.rotate(-270);
		assertEquals(90.0, t.getRotation());
		
		t.rotate(-90);
		assertEquals(0.0, t.getRotation());
		
		t.rotate(270);
		assertEquals(270.0, t.getRotation());
		
		t.rotate(90);
		assertEquals(0.0, t.getRotation());
		
		t.rotate(630);
		assertEquals(270.0, t.getRotation());
		
		t.rotate(90);
		assertEquals(0.0, t.getRotation());
		
		t.rotate(-630);
		assertEquals(90.0, t.getRotation());
	}
	
	public void testString(){
		String fileName = ".h";
		
		while(fileName.contains("..")){
			fileName = fileName.replace("..", ".");
		}
		
		if(fileName.lastIndexOf(".")==0){
			fileName = System.currentTimeMillis() + fileName;
		}
		if(fileName.startsWith(".")){
			fileName = fileName.substring(1, fileName.length());
		}
		System.out.println(fileName);
	}

}
