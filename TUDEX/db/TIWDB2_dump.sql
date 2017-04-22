--
-- PostgreSQL database dump
--

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

--
-- Name: depsequence_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE argsequence_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.argsequence_seq OWNER TO postgres;

--
-- Name: depsequence_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('argsequence_seq', 1, false);


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: ARGOMENTOTABLE; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ARGOMENTOTABLE (
    OID_2 integer DEFAULT nextval('argsequence_seq'::regclass) NOT NULL,
    NOME character varying(255),
    DESCRIZIONE character varying(255)
);


ALTER TABLE public.ARGOMENTOTABLE OWNER TO postgres;

--
-- Name: messsequence_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE messsequence_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.messsequence_seq OWNER TO postgres;

--
-- Name: messsequence_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('messsequence_seq', 3, true);


--
-- Name: employees; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE MESSAGGIOTABLE (
    OID_2 integer DEFAULT nextval('messsequence_seq'::regclass) NOT NULL,
    TITOLO character varying(255),
    CORPO character varying(255),
	DATA_ORA character varying(255),
    USEROID integer,
	MESSAGGIOID integer,
	ARGOMENTOOID integer
);


ALTER TABLE public.MESSAGGIOTABLE OWNER TO postgres;


--
-- Name: usersequence_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usersequence_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.usersequence_seq OWNER TO postgres;

--
-- Name: usersequence_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usersequence_seq', 3, true);


--
-- Name: employees; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE USERTABLE (
    OID_2 integer DEFAULT nextval('usersequence_seq'::regclass) NOT NULL,
    USERNAME character varying(255),
    PASSWORD character varying(255),
    EMAIL character varying(255),
	GROUPOID integer	
);


ALTER TABLE public.USERTABLE OWNER TO postgres;




--
-- Data for Name: ARGOMENTOTABLE; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY ARGOMENTOTABLE (OID_2, NOME, DESCRIZIONE) FROM stdin;
101	Argomento1	Descrizione1
102	Argomento2	Descrizione2
103	Argomento3	Descrizione3
104	Argomento4	Descrizione4
105	Argomento5	Descrizione5
\.

--
-- Data for Name: ARGOMENTOTABLE; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY MESSAGGIOTABLE (OID_2, TITOLO, CORPO, DATA_ORA, USEROID, MESSAGGIOID, ARGOMENTOOID) FROM stdin;
101	Messaggio1	Descrizione1	11/01/2001	101	101	103
\.

--
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY USERTABLE (OID_2, USERNAME, PASSWORD, EMAIL, GROUPOID) FROM stdin;
101	Guest	Guest	guestmail	1
\.


--
-- Name: ARGOMENTOTABLE_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ARGOMENTOTABLE
    ADD CONSTRAINT ARGOMENTOTABLE_pkey PRIMARY KEY (OID_2);


--
-- Name: MESSAGGIOTABLE_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY MESSAGGIOTABLE
    ADD CONSTRAINT MESSAGGIOTABLE_pkey PRIMARY KEY (OID_2);


--
-- Name: USERTABLE_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY USERTABLE
    ADD CONSTRAINT USERTABLE_pkey PRIMARY KEY (OID_2);



--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

