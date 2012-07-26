CREATE INDEX search_english ON product USING gin (to_tsvector('english', coalesce(name,'')||' '||coalesce(conservantstext,'')||' '||coalesce(tags,'')||' '||coalesce(company,'')));
CREATE INDEX search_english_productcategory ON productcategory USING gin (to_tsvector('english', coalesce(name,'')));

CREATE INDEX "e_l_number" ON e (lower(number::text));
CREATE INDEX "e_number" ON e (number);
CREATE INDEX "e_pkey_approved" ON e (id) WHERE approved=true;

CREATE INDEX "prod_entrydate_approved" ON product (entrydate);
CREATE INDEX "prod_entrydate_desc_approved" ON product (entrydate DESC);
CREATE INDEX "product_category_id"  ON product (category_id);

CREATE INDEX "pe_cons" ON product_e (conservants_id);
CREATE INDEX pe_prods ON product_e (products_id);

