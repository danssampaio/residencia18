CREATE TABLE IF NOT EXISTS public.usuario_login(
	id SERIAL PRIMARY KEY,
	login TEXT NOT NULL,
    senha TEXT NOT NULL,
    role TEXT NOT NULL
);