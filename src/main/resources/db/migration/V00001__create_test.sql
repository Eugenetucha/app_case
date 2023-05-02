CREATE TABLE IF NOT EXISTS public.line
(
    id
    bigint
    NOT
    NULL,
    company
    character
    varying
(
    255
) COLLATE pg_catalog."default",
    country character varying
(
    255
) COLLATE pg_catalog."default",
    credit boolean,
    name character varying
(
    255
) COLLATE pg_catalog."default",
    online_trade boolean,
    CONSTRAINT line_pkey PRIMARY KEY
(
    id
)
    )
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.line
    OWNER to postgres;
INSERT INTO public.line(id, company, country, credit, name, online_trade)
VALUES (0, 'samsung', 'russia', true, 'bapebone', true);
INSERT INTO public.line(id, company, country, credit, name, online_trade)
VALUES (1, 'apple', 'usa', false, 'lulu', true);
INSERT INTO public.line(id, company, country, credit, name, online_trade)
VALUES (2, 'digma', 'turkey', false, 'fdfd', true);
INSERT INTO public.line(id, company, country, credit, name, online_trade)
VALUES (3, 'amd', 'ukraine', true, 'aasd', false);
INSERT INTO public.line(id, company, country, credit, name, online_trade)
VALUES (4, 'intel', 'france', true, 'ewqe', false);
-- Table: public.model

-- DROP TABLE IF EXISTS public.model;

CREATE TABLE IF NOT EXISTS public.model
(
    id
    bigint
    NOT
    NULL,
    model_availability
    boolean,
    model_color
    character
    varying
(
    255
) COLLATE pg_catalog."default",
    model_name character varying
(
    255
) COLLATE pg_catalog."default",
    model_price integer,
    model_serial_number integer,
    model_size integer,
    line_id bigint,
    CONSTRAINT model_pkey PRIMARY KEY
(
    id
),
    CONSTRAINT fkfmaahn68wqra78vdxtcueg2m5 FOREIGN KEY
(
    line_id
)
    REFERENCES public.line
(
    id
) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.model
    OWNER to postgres;
INSERT INTO public.model(id, model_availability, model_color, model_name, model_price, model_serial_number, model_size,
                         line_id)
VALUES (0, true, 'black', 'bvcvbxc', 112, 323, 4, 0);
INSERT INTO public.model(id, model_availability, model_color, model_name, model_price, model_serial_number, model_size,
                         line_id)
VALUES (1, false, 'black', 'fgdfg', 564, 123, 4, 0);
INSERT INTO public.model(id, model_availability, model_color, model_name, model_price, model_serial_number, model_size,
                         line_id)
VALUES (2, false, 'red', 'xczxcz', 3, 12, 56, 0);
INSERT INTO public.model(id, model_availability, model_color, model_name, model_price, model_serial_number, model_size,
                         line_id)
VALUES (3, true, 'black', 'gffg', 1, 53, 6, 1);
INSERT INTO public.model(id, model_availability, model_color, model_name, model_price, model_serial_number, model_size,
                         line_id)
VALUES (4, false, 'green', 'asdsda', 44, 123, 23, 1);
INSERT INTO public.model(id, model_availability, model_color, model_name, model_price, model_serial_number, model_size,
                         line_id)
VALUES (5, true, 'black', 'gdfdfgdfg', 66, 1234, 6, 1);
INSERT INTO public.model(id, model_availability, model_color, model_name, model_price, model_serial_number, model_size,
                         line_id)
VALUES (6, true, 'black', 'zxczxc', 88, 5353, 12, 2);
INSERT INTO public.model(id, model_availability, model_color, model_name, model_price, model_serial_number, model_size,
                         line_id)
VALUES (7, true, 'green', 'gfdfdg', 33, 511, 4, 2);
INSERT INTO public.model(id, model_availability, model_color, model_name, model_price, model_serial_number, model_size,
                         line_id)
VALUES (8, true, 'green', 'vccvxxcv', 22, 123123, 213, 2);
INSERT INTO public.model(id, model_availability, model_color, model_name, model_price, model_serial_number, model_size,
                         line_id)
VALUES (9, true, 'black', 'zxczxc', 88, 5353, 12, 3);
INSERT INTO public.model(id, model_availability, model_color, model_name, model_price, model_serial_number, model_size,
                         line_id)
VALUES (10, true, 'green', 'gfdfdg', 33, 511, 4, 3);
INSERT INTO public.model(id, model_availability, model_color, model_name, model_price, model_serial_number, model_size,
                         line_id)
VALUES (11, true, 'green', 'vccvxxcv', 22, 123123, 213, 3);
INSERT INTO public.model(id, model_availability, model_color, model_name, model_price, model_serial_number, model_size,
                         line_id)
VALUES (12, true, 'green', 'vccvxxcv', 22, 123123, 213, 4);
INSERT INTO public.model(id, model_availability, model_color, model_name, model_price, model_serial_number, model_size,
                         line_id)
VALUES (13, true, 'black', 'zxczxc', 88, 5353, 12, 4);
INSERT INTO public.model(id, model_availability, model_color, model_name, model_price, model_serial_number, model_size,
                         line_id)
VALUES (14, true, 'green', 'gfdfdg', 33, 511, 4, 4);
-- Table: public.parameters

-- DROP TABLE IF EXISTS public.parameters;

CREATE TABLE IF NOT EXISTS public.parameters
(
    id
    bigint
    NOT
    NULL,
    key
    character
    varying
(
    255
) COLLATE pg_catalog."default",
    value character varying
(
    255
) COLLATE pg_catalog."default",
    model_id bigint,
    CONSTRAINT parameters_pkey PRIMARY KEY
(
    id
),
    CONSTRAINT fkscj0ek3hdvclj734kjym7edj2 FOREIGN KEY
(
    model_id
)
    REFERENCES public.model
(
    id
) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.parameters
    OWNER to postgres;
INSERT INTO public.parameters(
    id, key, value, model_id)
VALUES (0, 'key', 'value', 0);
INSERT INTO public.parameters(
    id, key, value, model_id)
VALUES (1, 'key', 'value', 1);
INSERT INTO public.parameters(
    id, key, value, model_id)
VALUES (2, 'key', 'value', 2);
INSERT INTO public.parameters(
    id, key, value, model_id)
VALUES (3, 'key', 'value', 3);
INSERT INTO public.parameters(
    id, key, value, model_id)
VALUES (4, 'key', 'value', 4);
INSERT INTO public.parameters(
    id, key, value, model_id)
VALUES (5, 'key', 'value', 5);

-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id
    bigint
    NOT
    NULL,
    is_non_locket
    boolean,
    password
    character
    varying
(
    255
) COLLATE pg_catalog."default",
    role character varying
(
    255
) COLLATE pg_catalog."default",
    username character varying
(
    255
) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY
(
    id
)
    )
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;
INSERT INTO public.users(
    id, is_non_locket, password, role, username)
VALUES (0, true, '$2a$10$G8w3ST7Uwb73pkSfc.ePBOCDtYksOqJ9ilXfLlPrkm4s3O5p6zD5.', 'user', '123');