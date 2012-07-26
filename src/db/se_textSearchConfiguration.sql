-- paieškos konfigūracija produktų lentelei

CREATE TEXT SEARCH CONFIGURATION public.inbelly_swedish
	(COPY = pg_catalog.swedish);

CREATE TEXT SEARCH DICTIONARY inbelly_swedish_syn (
	TEMPLATE = synonym,
	SYNONYMS = swedish
);

CREATE TEXT SEARCH DICTIONARY inbelly_swedish_ispell (
	TEMPLATE = ispell,
	DictFile = swedish,
	AffFile = swedish,
	StopWords = swedish
);

ALTER TEXT SEARCH CONFIGURATION public.inbelly_swedish
	ALTER MAPPING FOR asciiword,asciihword,hword_asciipart, word, hword, hword_part
	WITH inbelly_swedish_syn, swedish_stem;

SET default_text_search_config = 'inbelly_swedish';

-- paieškos konfigūracija E lentelei -- ten daug chemijos terminų, tai šveplus juos reik palikt angliškam konfigui, kitką nagrinėjam ir mes

CREATE TEXT SEARCH CONFIGURATION public.swedish_e
	(COPY = pg_catalog.swedish);

ALTER TEXT SEARCH CONFIGURATION public.swedish_e
	ALTER MAPPING FOR word, hword, hword_part
	WITH swedish_stem;
	
CREATE TEXT SEARCH DICTIONARY public.sveikasvaikas_brands (
	TEMPLATE = thesaurus,
	DictFile = brands,
	Dictionary = simple);
	
ALTER TEXT SEARCH CONFIGURATION inbelly_swedish
	ALTER MAPPING FOR asciiword,asciihword,hword_asciipart, word, hword, hword_part
	WITH sveikasvaikas_brands, swedish_stem;
	
ALTER TEXT SEARCH CONFIGURATION public.swedish_e
	ALTER MAPPING FOR word, hword, hword_part
	WITH sveikasvaikas_brands, swedish_stem;
