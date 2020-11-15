CREATE TABLE public.comments
(
  id bigserial PRIMARY KEY NOT NULL,
  commnet VARCHAR(255),
  time timestamp NOT NULL
);
CREATE UNIQUE INDEX comments_id_uindex ON public.comments (id);

CREATE TABLE public.notices
(
  id bigserial PRIMARY KEY NOT NULL,
  comment_id bigint NOT NULL,
  time timestamp NOT NULL,
  delivered boolean NOT NULL
);
CREATE UNIQUE INDEX notices_id_uindex ON public.notices (id);