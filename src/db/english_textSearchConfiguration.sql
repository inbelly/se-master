-- paieškos konfigūracija produktų lentelei

CREATE TEXT SEARCH CONFIGURATION public.inbelly_english
	(COPY = pg_catalog.english);

CREATE TEXT SEARCH DICTIONARY inbelly_english_syn (
	TEMPLATE = synonym,
	SYNONYMS = english
);

CREATE TEXT SEARCH DICTIONARY inbelly_english_ispell (
	TEMPLATE = ispell,
	DictFile = english,
	AffFile = english,
	StopWords = english
);

ALTER TEXT SEARCH CONFIGURATION public.inbelly_english
	ALTER MAPPING FOR asciiword,asciihword,hword_asciipart, word, hword, hword_part
	WITH inbelly_english_syn, english_stem;

SET default_text_search_config = 'inbelly_english';

-- paieškos konfigūracija E lentelei -- ten daug chemijos terminų, tai šveplus juos reik palikt angliškam konfigui, kitką nagrinėjam ir mes

CREATE TEXT SEARCH CONFIGURATION public.english_e
	(COPY = pg_catalog.english);

ALTER TEXT SEARCH CONFIGURATION public.english_e
	ALTER MAPPING FOR word, hword, hword_part
	WITH english_stem;
	
CREATE TEXT SEARCH DICTIONARY public.sveikasvaikas_brands (
	TEMPLATE = thesaurus,
	DictFile = brands,
	Dictionary = simple);
	
ALTER TEXT SEARCH CONFIGURATION inbelly_english
	ALTER MAPPING FOR asciiword,asciihword,hword_asciipart, word, hword, hword_part
	WITH sveikasvaikas_brands, english_stem;
	
ALTER TEXT SEARCH CONFIGURATION public.english_e
	ALTER MAPPING FOR word, hword, hword_part
	WITH sveikasvaikas_brands, english_stem;