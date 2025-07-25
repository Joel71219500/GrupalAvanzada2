PGDMP      5                }            Online_Bookstore    17.2    17.2 /    (           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            )           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            *           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            +           1262    16847    Online_Bookstore    DATABASE     �   CREATE DATABASE "Online_Bookstore" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
 "   DROP DATABASE "Online_Bookstore";
                     postgres    false            �            1259    16854    author    TABLE     n   CREATE TABLE public.author (
    id integer NOT NULL,
    name character varying(255),
    version integer
);
    DROP TABLE public.author;
       public         heap r       postgres    false            �            1259    16853    author_id_seq    SEQUENCE     �   CREATE SEQUENCE public.author_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.author_id_seq;
       public               postgres    false    219            ,           0    0    author_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.author_id_seq OWNED BY public.author.id;
          public               postgres    false    218            �            1259    16848    book    TABLE     �   CREATE TABLE public.book (
    isbn character varying(13) NOT NULL,
    title character varying(255),
    price numeric(8,2),
    version integer
);
    DROP TABLE public.book;
       public         heap r       postgres    false            �            1259    16860    book_author    TABLE     t   CREATE TABLE public.book_author (
    books_isbn character varying(13) NOT NULL,
    authors_id integer NOT NULL
);
    DROP TABLE public.book_author;
       public         heap r       postgres    false            �            1259    16876    customer    TABLE     �   CREATE TABLE public.customer (
    id integer NOT NULL,
    name character varying(255),
    email character varying(255),
    version integer
);
    DROP TABLE public.customer;
       public         heap r       postgres    false            �            1259    16875    customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.customer_id_seq;
       public               postgres    false    222            -           0    0    customer_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;
          public               postgres    false    221            �            1259    16913 	   inventory    TABLE     x   CREATE TABLE public.inventory (
    book_isbn character varying(13) NOT NULL,
    supplied integer,
    sold integer
);
    DROP TABLE public.inventory;
       public         heap r       postgres    false            �            1259    16897    lineitem    TABLE     �   CREATE TABLE public.lineitem (
    idx integer NOT NULL,
    order_id integer,
    book_isbn character varying(13),
    quantity integer
);
    DROP TABLE public.lineitem;
       public         heap r       postgres    false            �            1259    16896    lineitem_idx_seq    SEQUENCE     �   CREATE SEQUENCE public.lineitem_idx_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.lineitem_idx_seq;
       public               postgres    false    226            .           0    0    lineitem_idx_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.lineitem_idx_seq OWNED BY public.lineitem.idx;
          public               postgres    false    225            �            1259    16885    purchaseorder    TABLE     �   CREATE TABLE public.purchaseorder (
    id integer NOT NULL,
    customer_id integer,
    placedon timestamp without time zone,
    deliveredon timestamp without time zone,
    status integer,
    total numeric(8,2)
);
 !   DROP TABLE public.purchaseorder;
       public         heap r       postgres    false            �            1259    16884    purchaseorder_id_seq    SEQUENCE     �   CREATE SEQUENCE public.purchaseorder_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.purchaseorder_id_seq;
       public               postgres    false    224            /           0    0    purchaseorder_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.purchaseorder_id_seq OWNED BY public.purchaseorder.id;
          public               postgres    false    223            r           2604    16857 	   author id    DEFAULT     f   ALTER TABLE ONLY public.author ALTER COLUMN id SET DEFAULT nextval('public.author_id_seq'::regclass);
 8   ALTER TABLE public.author ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    219    218    219            s           2604    16879    customer id    DEFAULT     j   ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);
 :   ALTER TABLE public.customer ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    222    221    222            u           2604    16900    lineitem idx    DEFAULT     l   ALTER TABLE ONLY public.lineitem ALTER COLUMN idx SET DEFAULT nextval('public.lineitem_idx_seq'::regclass);
 ;   ALTER TABLE public.lineitem ALTER COLUMN idx DROP DEFAULT;
       public               postgres    false    226    225    226            t           2604    16888    purchaseorder id    DEFAULT     t   ALTER TABLE ONLY public.purchaseorder ALTER COLUMN id SET DEFAULT nextval('public.purchaseorder_id_seq'::regclass);
 ?   ALTER TABLE public.purchaseorder ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    223    224    224                      0    16854    author 
   TABLE DATA           3   COPY public.author (id, name, version) FROM stdin;
    public               postgres    false    219   85                 0    16848    book 
   TABLE DATA           ;   COPY public.book (isbn, title, price, version) FROM stdin;
    public               postgres    false    217   �5                 0    16860    book_author 
   TABLE DATA           =   COPY public.book_author (books_isbn, authors_id) FROM stdin;
    public               postgres    false    220   I6                  0    16876    customer 
   TABLE DATA           <   COPY public.customer (id, name, email, version) FROM stdin;
    public               postgres    false    222   �6       %          0    16913 	   inventory 
   TABLE DATA           >   COPY public.inventory (book_isbn, supplied, sold) FROM stdin;
    public               postgres    false    227   �6       $          0    16897    lineitem 
   TABLE DATA           F   COPY public.lineitem (idx, order_id, book_isbn, quantity) FROM stdin;
    public               postgres    false    226   C7       "          0    16885    purchaseorder 
   TABLE DATA           ^   COPY public.purchaseorder (id, customer_id, placedon, deliveredon, status, total) FROM stdin;
    public               postgres    false    224   �7       0           0    0    author_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.author_id_seq', 6, true);
          public               postgres    false    218            1           0    0    customer_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.customer_id_seq', 2, true);
          public               postgres    false    221            2           0    0    lineitem_idx_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.lineitem_idx_seq', 3, true);
          public               postgres    false    225            3           0    0    purchaseorder_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.purchaseorder_id_seq', 2, true);
          public               postgres    false    223            y           2606    16859    author author_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.author DROP CONSTRAINT author_pkey;
       public                 postgres    false    219            {           2606    16864    book_author book_author_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.book_author
    ADD CONSTRAINT book_author_pkey PRIMARY KEY (books_isbn, authors_id);
 F   ALTER TABLE ONLY public.book_author DROP CONSTRAINT book_author_pkey;
       public                 postgres    false    220    220            w           2606    16852    book book_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (isbn);
 8   ALTER TABLE ONLY public.book DROP CONSTRAINT book_pkey;
       public                 postgres    false    217            }           2606    16883    customer customer_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public                 postgres    false    222            �           2606    16917    inventory inventory_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.inventory
    ADD CONSTRAINT inventory_pkey PRIMARY KEY (book_isbn);
 B   ALTER TABLE ONLY public.inventory DROP CONSTRAINT inventory_pkey;
       public                 postgres    false    227            �           2606    16902    lineitem lineitem_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.lineitem
    ADD CONSTRAINT lineitem_pkey PRIMARY KEY (idx);
 @   ALTER TABLE ONLY public.lineitem DROP CONSTRAINT lineitem_pkey;
       public                 postgres    false    226                       2606    16890     purchaseorder purchaseorder_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.purchaseorder
    ADD CONSTRAINT purchaseorder_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.purchaseorder DROP CONSTRAINT purchaseorder_pkey;
       public                 postgres    false    224            �           2606    16870 '   book_author book_author_authors_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.book_author
    ADD CONSTRAINT book_author_authors_id_fkey FOREIGN KEY (authors_id) REFERENCES public.author(id);
 Q   ALTER TABLE ONLY public.book_author DROP CONSTRAINT book_author_authors_id_fkey;
       public               postgres    false    4729    220    219            �           2606    16865 '   book_author book_author_books_isbn_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.book_author
    ADD CONSTRAINT book_author_books_isbn_fkey FOREIGN KEY (books_isbn) REFERENCES public.book(isbn);
 Q   ALTER TABLE ONLY public.book_author DROP CONSTRAINT book_author_books_isbn_fkey;
       public               postgres    false    4727    220    217            �           2606    16918 "   inventory inventory_book_isbn_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.inventory
    ADD CONSTRAINT inventory_book_isbn_fkey FOREIGN KEY (book_isbn) REFERENCES public.book(isbn);
 L   ALTER TABLE ONLY public.inventory DROP CONSTRAINT inventory_book_isbn_fkey;
       public               postgres    false    217    4727    227            �           2606    16908     lineitem lineitem_book_isbn_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.lineitem
    ADD CONSTRAINT lineitem_book_isbn_fkey FOREIGN KEY (book_isbn) REFERENCES public.book(isbn);
 J   ALTER TABLE ONLY public.lineitem DROP CONSTRAINT lineitem_book_isbn_fkey;
       public               postgres    false    217    226    4727            �           2606    16903    lineitem lineitem_order_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.lineitem
    ADD CONSTRAINT lineitem_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.purchaseorder(id);
 I   ALTER TABLE ONLY public.lineitem DROP CONSTRAINT lineitem_order_id_fkey;
       public               postgres    false    224    4735    226            �           2606    16891 ,   purchaseorder purchaseorder_customer_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.purchaseorder
    ADD CONSTRAINT purchaseorder_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customer(id);
 V   ALTER TABLE ONLY public.purchaseorder DROP CONSTRAINT purchaseorder_customer_id_fkey;
       public               postgres    false    222    224    4733               Z   x�3�tOL*�L�QpO,J>�6Q���¢���*NC.#N/=o=����̼t��1�{j~Qz��QyjNPĄ�+1/U����$5ȏ���� ��         �   x�5���0�������v�+e�1�
CdKN(�V��b��{?4E���M�=�!T$�w��F�pJ��zn��]�=���V�7�Y��tI0�`ig'/"W�ޯ�~ٵ-�Stf���A�F�^aRͲ��G.W�h|�l�R�.�         <   x��� 1�7.&�1��뿎�h_#mg9��C�	=5��i�Y�1�2>�?�ǲ�� \�Hk          I   x�3��*M�S8��(��3��+H2R+srR���s9��8}�2|o. ��q�r�0��qqq !�$      %   E   x�%���0Cѳ��	�K���(�oO��E��G�	��`x��P���̘�B緉�UHbm{3� ��      $   8   x�-��  �7c8�����sH��H�6�	�@��b�����`�����T�lR
�      "   H   x�u̱�0��pb���jq�uؙ������hy�Ǽ�zfq�чe���W��C���1U}���     