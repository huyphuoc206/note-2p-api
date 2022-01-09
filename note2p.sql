PGDMP                 	         z            note-2p #   12.9 (Ubuntu 12.9-0ubuntu0.20.04.1) #   12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16537    note-2p    DATABASE     {   CREATE DATABASE "note-2p" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';
    DROP DATABASE "note-2p";
                postgres    false            �            1259    16538    users    TABLE       CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    fullname character varying(255) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    is_active boolean NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    16544    account_id_seq    SEQUENCE     �   ALTER TABLE public.users ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    202            �            1259    16588    category    TABLE     �   CREATE TABLE public.category (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    is_deleted boolean NOT NULL,
    user_id bigint NOT NULL
);
    DROP TABLE public.category;
       public         heap    postgres    false            �            1259    16586    category_id_seq    SEQUENCE     �   ALTER TABLE public.category ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    207            �            1259    16546    tasks    TABLE       CREATE TABLE public.tasks (
    id bigint NOT NULL,
    name text NOT NULL,
    is_done boolean NOT NULL,
    is_deleted boolean NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone,
    category_id bigint NOT NULL
);
    DROP TABLE public.tasks;
       public         heap    postgres    false            �            1259    16552    tasks_id_seq    SEQUENCE     �   ALTER TABLE public.tasks ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.tasks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    204            �          0    16588    category 
   TABLE DATA           Z   COPY public.category (id, title, created_at, updated_at, is_deleted, user_id) FROM stdin;
    public          postgres    false    207   �       �          0    16546    tasks 
   TABLE DATA           c   COPY public.tasks (id, name, is_done, is_deleted, created_at, updated_at, category_id) FROM stdin;
    public          postgres    false    204   �       �          0    16538    users 
   TABLE DATA           U   COPY public.users (id, email, password, fullname, created_at, is_active) FROM stdin;
    public          postgres    false    202   �!       �           0    0    account_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.account_id_seq', 29, true);
          public          postgres    false    203            �           0    0    category_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.category_id_seq', 26, true);
          public          postgres    false    206            �           0    0    tasks_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.tasks_id_seq', 69, true);
          public          postgres    false    205            #           2606    16592    category category_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.category DROP CONSTRAINT category_pkey;
       public            postgres    false    207            !           2606    16557    tasks tasks_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.tasks DROP CONSTRAINT tasks_pkey;
       public            postgres    false    204                       2606    16555    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    202            $           2606    16598    tasks task_category_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT task_category_id FOREIGN KEY (category_id) REFERENCES public.category(id) NOT VALID;
 @   ALTER TABLE ONLY public.tasks DROP CONSTRAINT task_category_id;
       public          postgres    false    2851    207    204            %           2606    16593    category user_category_id    FK CONSTRAINT     x   ALTER TABLE ONLY public.category
    ADD CONSTRAINT user_category_id FOREIGN KEY (user_id) REFERENCES public.users(id);
 C   ALTER TABLE ONLY public.category DROP CONSTRAINT user_category_id;
       public          postgres    false    207    202    2847            �   �  x��U�n1<{��w��v�u�"�"��q�2�,y��A�|�/��8!�l�ɏ��O��g#q-�������L�7�?���{:�i��Q��l��	�q'�t:q8�w���=�|ʘd�"�5n���L̷Cc��^�	Ai�c&*�͠����1aH���8Ƒzƕ@j�xy���v^=�EZ.w�Y|�Q�~�\�'7�r�Y�Z��U[��]�Z��5�}�s�f�����T^����C�1Y���N\RT����QW*;!���CQ�d��lc�����	�=�����X.�����������M]����븟oU��VD�,��d�)�&�v@j?rD���=�����>�ri�F��{���A;�z�.<��P�Y������f��;sp��1;\G$pTtnLaJ/
�of�c������1�O����n@�xj1��1=��[����	�U�1�~��2#��ĩtz�v�rL�W٪Wd{#N��l;RN�Hr��)�P�>o�ԥ�A��V3�z�]̦+9��<�p�u+�L���O�q��'t5/��U0l��#y�W�S�wS�f���R3�|���?w��r��	N�3L�)�H���]��LS��yx�٘���O�8/��q<PDna���
꿝�C �h����j<�ˡ��w�i�IR��      �   �  x��XMo��]?���*RT����fg���©�xZ#@74E��h��E�,��͢�l�Fڴ����� Ȃ������G�%�|#AA�����׹����ѣCzX�MR�ْ4����;��)����Da�\�Pk�y�y�X�)�_U�r��(V�
�&��rh7o}.�(9�,����D��	"�������2)O=���$��r,g:����לΊ���9�}���9�M�U ޮ/�d����,d��%Dg@�߮��Jz��@��2����EJߛ��7�Zo7�&������>#]B\�1qg��_?����iV�tZͲ���~��,�:��ރ!��m¨�#����SD�x��M$m<Lv#"�p,a\pA�q��˵4z(�'܄\Ib�����q}<����N�I�:�o9�YDj_�YZͬG�Ci%с���?k�;��E[���tA��H�o��.�8L�2R^��w82"�O�E��,;�����>���8���*�FM�N2�A�2K�N�]��Հ�ϋ&�i�ԙ��0�hm(��iUҴ�����.7��[���B�21y��͓jy@�+z����M��	�؈����j��~vT��E b��A5�Wg���u���P�dZ|\��I��UY� �p�U�~����Co!�#���kz�8)���rH���N�`T#��L�E���Z�l "�2Yݒ��#D<�T����V	m���^}������ߏ#��V(�h�.Wr�I���՜6���G�i���%H{T�Wr�'{<�i����`B��=`;�S�!bS��YC����Y84�r 1��iB�zAO�v��4Y�����}�ݼLh�>3��H�u.%G� �咃f�rASĲ�f�K\�4���s%w�0R0_7��/�&f����&f��wm``O���K&t�������@g���i�>�����;ꐡ|x��k�ϋ	=ޮ�Y�q]��i�>/P���j�(���n>�i����҅�3�Q�:��r5�QE�}<�A�'
-�GhWr8"��h��W�y���m�A��ʝvLʬ9����_���:fr%g"�<vAh�%(�ǣۂ���)��u���G<T��즜��A���ʽr�JK~������E��sO2�v8Ƈ�qS`Zߞ�=Ņ�� j�\lo��8b3Q�v��k9S��Z�oԆi��;8u�W����0O�>_��]����+?I)	.NGD+ZGQ��d�� �-��2�+F�����O���ʎ��Ά����Ax�M�ƣ-BHA��YU�<���/�2�>����)�� �W�y����ܞ1/<;S�-BOB��F����t������@�غ�_�gI��V�߬|��-��zp�L�.�a2MJ��4v��H�Z������>̚�ŭ��}-�&O�t�5�WEs��fX�7����(3�4�f�wgM1O��2;��N|��)��b�b76�Xv�n+��1n?J9�Q�5G�X:��>�]r�����cN���H�yx@ϒ�>��ΒS�"]����Bh)K~�-��8+�zT�1K����4'����"\tx��}���Ew8��R���\:�cXJ�4��z?i�ړ!��;���K�A�7H�m�}�Êqb
۫*������`�j���z(�R�C�� �jw����y�W��9��^V�J��}�ު";|B���NrT��04�jY4�@rw���q�]4���.c�Ų����#�C�Vf`&=�"���W��j��1rø�4Q�?�A��
��      �   �  x�u�I��@ ��uy
n�� Ű
�ρH� ۗM1�M z��D����I4���w����Dy�9+��F��`4��E;�zhy��kg��k�S
O���ڦ}��,�ˢ�+m$���6��"���׏�D�C��	2	3
�FA�#ԉ�մu�N��lrNx��"�m�g4f��E^y<��򬔂�������}}�T�D��5�VH1�0�U��p��h�k�u���t��#�*|'m7|yYT�_�V�Id}d�*5�'Qys)�}����N��9�Ug��3�i�5_e���|�Uh�V��V���]��m7�g�f1ro���*L���=��E<a�U��q��K(u/���'Z�t'��|N݃R%IW���P,��Sn$����z������     