-- paieškos konfigūracija produktų lentelei

CREATE TEXT SEARCH CONFIGURATION public.lietuviu 
	(COPY = pg_catalog.english);

CREATE TEXT SEARCH DICTIONARY lietuviu_syn (
	TEMPLATE = synonym,
	SYNONYMS = lietuviu
);

CREATE TEXT SEARCH DICTIONARY lietuviu_ispell (
	TEMPLATE = ispell,
	DictFile = lietuviu,
	AffFile = lietuviu,
	StopWords = lietuviu
);

ALTER TEXT SEARCH CONFIGURATION public.lietuviu
	ALTER MAPPING FOR asciiword,asciihword,hword_asciipart, word, hword, hword_part
	WITH lietuviu_syn, lietuviu_ispell;
	
SET default_text_search_config = 'public.lietuviu';

-- paieškos konfigūracija E lentelei -- ten daug chemijos terminų, tai šveplus juos reik palikt angliškam konfigui, kitką nagrinėjam ir mes

CREATE TEXT SEARCH CONFIGURATION public.lietuviu_e
	(COPY = pg_catalog.english);


ALTER TEXT SEARCH CONFIGURATION public.lietuviu_e
	ALTER MAPPING FOR word, hword, hword_part
	WITH lietuviu_syn, lietuviu_ispell;
	
SET default_text_search_config = 'public.lietuviu';

CREATE TEXT SEARCH DICTIONARY public.sveikasvaikas_brands (
	TEMPLATE = thesaurus,
	DictFile = brands,
	Dictionary = simple);
	
ALTER TEXT SEARCH CONFIGURATION public.lietuviu
	ALTER MAPPING FOR asciiword,asciihword,hword_asciipart, word, hword, hword_part
	WITH sveikasvaikas_brands, lietuviu_syn, lietuviu_ispell, english_stem;
	
ALTER TEXT SEARCH CONFIGURATION public.lietuviu_e
	ALTER MAPPING FOR word, hword, hword_part
	WITH sveikasvaikas_brands, lietuviu_syn, lietuviu_ispell, english_stem;