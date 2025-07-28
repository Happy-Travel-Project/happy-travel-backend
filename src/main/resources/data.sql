--admin: Admin123##
--user: User123##
--Ana Carina: Ana123##
--Thais: Thais123##
--Paola: Paola123##
-- Morena: Morena123##

-- USERS
INSERT INTO users (id, username, email, password, role) VALUES
  (1, 'admin', 'admin@admin.com', '$2a$10$bJLFDW.zD83bQDb4mM3kB.U3fu9kK74kUP5SxPUsxH1PDAH6MBJne', 'ADMIN'),
  (2, 'user', 'user@user.com', '$2a$10$zFZshAO0itrG/5cFJXVCue78PjD7dywTdOVFgZYW2cklZkef03pea', 'USER'),
  (3, 'Ana Carina', 'ana@email.com', '$2a$10$GMWMPYxj2.DmdHB4g59a.uPtVtF3nDnSJb1dd7CqIAEkcQ9smDKvu', 'USER'),
  (4, 'Thais', 'thais@email.com', '$2a$10$2A5IZUcAoxCdnzz6xOmZTejUTfPC6CijZH8R71cPUrnUxXQihdKbK', 'USER'),
  (5, 'Paola', 'paola@email.com', '$2a$10$Wjj1kpvVg.nrh4BLW.BEwO/pDqCvSe0j9zmrDOw1l8swZYaC4/Zca', 'USER'),
  (6, 'Morena', 'morena@email.com', '$2a$10$vXH79Eyg1uJfrsOHF4cg1u/b.jbC7MaBvpnhrW5kwbboGg9ZNBhAa', 'USER');

INSERT INTO destinations (id, title, country, city, image, description, user_id) VALUES
  -- ARGENTINA
  (1, 'Iguazú Falls', 'Argentina 🇦🇷', 'Misiones', 'https://media.istockphoto.com/id/1159474019/es/foto/parte-de-las-cataratas-del-iguaz%C3%BA-vista-desde-el-parque-nacional-argentino.jpg?s=2048x2048&w=is&k=20&c=GU25mFa8658vKC9M6SaY-qNytZ-wvBvwqq-7Lmuh7Ko=', 'Massive waterfalls surrounded by lush jungle, a UNESCO site and one of the most breathtaking natural wonders of South America.', 6),
  (5, 'Perito Moreno Glacier', 'Argentina 🇦🇷', 'El Calafate', 'https://media.istockphoto.com/id/941435716/es/foto/panorama-del-glaciar-perito-moreno-en-la-patagonia.jpg?s=2048x2048&w=is&k=20&c=KRxPhqv9RT1c3rBg09kt0tHN1_80iRP8aoqErL0BkOE=', 'A stunning advancing glacier in Patagonia, known for its spectacular ice collapses and walkable viewing platforms.', 6),
  (9, 'Quebrada de Humahuaca', 'Argentina 🇦🇷', 'Jujuy', 'https://media.istockphoto.com/id/1391248627/es/foto/el-hornocal-jujuy-argentina.jpg?s=2048x2048&w=is&k=20&c=aPd9y9DJTvJWBAaZbwPKrThKu9tHTvAt_PR7Dxc1tsk=', 'A colorful valley in the Argentine north with Andean culture, colonial towns, and the famous Hill of Seven Colors.', 6),
  (13, 'Botanical Garden', 'Argentina 🇦🇷', 'Tucumán', 'https://img.lagaceta.com.ar/fotos/notas/2022/09/15/1100x733_treveln-961483-170646.webp', 'Lush garden filled with native flowers and trees, offering a peaceful escape and an impressive collection of regional flora.', 2),
  (17, 'Valle de la Luna', 'Argentina 🇦🇷', 'San Juan', 'https://media.istockphoto.com/id/178382083/es/foto/sanstone-cliffs-talampaya.jpg?s=612x612&w=0&k=20&c=MxPbq2nbTMz9DWW6O_sCVIb6D-uAPVsSP2eLzMXgl4M=', 'A surreal desert landscape shaped by wind and time, known for its moon-like terrain and rich fossil discoveries.', 6),

-- ECUADOR
  (2, 'Galápagos Islands', 'Ecuador 🇪🇨', 'Galápagos', 'https://media.istockphoto.com/id/470028282/es/foto/vista-desde-la-isla-bartolom%C3%A9.jpg?s=612x612&w=0&k=20&c=KvxKeLhuzFjT8hJbfNgXI0qiC4ZzxjyVZHi4D0AyDCo=', 'A natural laboratory with giant tortoises, unique birds, and volcanic landscapes, famous for inspiring Darwin's theory.', 3),
  (6, 'Quito Historic Center', 'Ecuador 🇪🇨', 'Quito', 'https://media.istockphoto.com/id/1297519788/es/foto/guayaquil-ecuador.jpg?s=612x612&w=0&k=20&c=DyG7XQiIN1-NfX-Kv_Mb8FBrkGlxRLnC2QNX1yWFC0Q=', 'Colonial-era churches, plazas and Baroque architecture in one of Latin America's best-preserved old towns.', 3),
  (10, 'Middle of the World', 'Ecuador 🇪🇨', 'Quito', 'https://media.istockphoto.com/id/92250630/es/foto/l%C3%ADnea-del-ecuador-en-quito-ecuador.jpg?s=612x612&w=0&k=20&c=jToUQBkqGs_VcGLKNRQN4QNVnpMZdI9sJ__tfXLRNP8=', 'A unique spot where you can stand on the equator line—experience science, folklore, and scenic views.', 1),
  (14, 'Baños de Agua Santa', 'Ecuador 🇪🇨', 'Baños', 'https://media.istockphoto.com/id/1201308064/es/foto/el-caldero-del-diablo-cascada-cerca-de-banos-ecuador.jpg?s=612x612&w=0&k=20&c=wabrid-xZx4P28gIGNfl8E8KTjnZqw6T6gxGWCHnFQ8=', 'A charming town famous for waterfalls, hot springs, and adventure sports at the edge of the Amazon.', 3),
  (18, 'Cotopaxi National Park', 'Ecuador 🇪🇨', 'Latacunga', 'https://media.istockphoto.com/id/2160490540/es/foto/vista-a%C3%A9rea-de-una-mujer-y-un-hombre-mirando-en-el-lago-quilotoa-durante-un-viaje-a-ecuador.jpg?s=612x612&w=0&k=20&c=IWdeYAzTV0IekM_bV5c7t1rQS6PVV5UnpiRb8-vc0J8=', 'Home to the iconic Cotopaxi volcano, this park offers hiking, wildlife, and stunning Andean landscapes.', 3),

  -- BRAZIL
  (3, 'Christ the Redeemer', 'Brazil 🇧🇷', 'Rio de Janeiro', 'https://media.istockphoto.com/id/1312539295/es/foto/vista-a%C3%A9rea-del-santuario-de-cristo-rey-o-santuario-de-cristo-rei-en-el-soleado-d%C3%ADa-de-verano.jpg?s=612x612&w=0&k=20&c=hqShhdtmuYMwZ3ClYtWItKsWGp2NPYYglGq8M5g912U=', 'Towering above Rio, this iconic statue offers unmatched views and spiritual significance.', 1),
  (7, 'Arraial do Cabo', 'Brazil 🇧🇷', 'Rio de Janeiro', 'https://media.istockphoto.com/id/577307658/es/foto/pontal-de-atalaia-arraial-do-cabo-r%C3%ADo-de-janeiro-brasil.jpg?s=612x612&w=0&k=20&c=XYa4d0rzrS1zppXyBK-0R1ax1FyOz2vFoHI3Y0QCH5A=', 'Often called "Brazilian Caribbean," its turquoise waters and sand dunes are a dream for beach lovers.', 4),
  (11, 'Lençóis Maranhenses', 'Brazil 🇧🇷', 'Barreirinhas', 'https://media.istockphoto.com/id/1367386194/es/foto/parque-nacional-lencois-maranhenses-paisaje-de-dunas-y-lagos-de-agua-de-lluvia-barreirinhas-ma.jpg?s=612x612&w=0&k=20&c=VW2dpfmDabZQc1_x76AvQxdabiED8GAagl-w2mJ6I50=', 'A surreal landscape of white sand dunes and seasonal freshwater lagoons, offering one of Brazil's most unique natural experiences.', 4),
  (15, 'Salvador de Bahía', 'Brazil 🇧🇷', 'Salvador', 'https://media.istockphoto.com/id/540586506/es/foto/colonial-coloridas-casas-en-pelourinho-salvador-bahia-brasil.jpg?s=2048x2048&w=is&k=20&c=bFxEWnXvyAireoh5Ywo795gjnh0W2lS8FsbKU4yPR8Y=', 'A melting pot of African-Brazilian culture, colonial charm, music, and tropical coastline.', 2),
  (19, 'Pantanal', 'Brazil 🇧🇷', 'Mato Grosso', 'https://media.istockphoto.com/id/1475054967/es/foto/r%C3%ADo-sucuri-o-r%C3%ADo-sucuri-en-bonito-mato-grosso-do-sul-r%C3%ADo-con-agua-cristalina-azul-brasil.jpg?s=612x612&w=0&k=20&c=gjs_1d2iVy4X9c1N2Czoh6JL63sXSZXWvk5flg1EwM0=', 'The world's largest tropical wetland, teeming with wildlife and offering unique eco-tourism experiences.', 4),

  -- COLOMBIA
  (4, 'San Andrés Island', 'Colombia 🇨🇴', 'San Andrés', 'https://media.istockphoto.com/id/587782960/es/foto/san-andr%C3%A9s.jpg?s=612x612&w=0&k=20&c=aohl5W-Y8QtECFQgt5JiSo3LN8pkc_Bc8m-lWVTPRLg=', 'Crystal-clear Caribbean waters, white beaches and coral reefs make this island a paradise for divers and beach lovers.', 5),
  (8, 'Cartagena Old Town', 'Colombia 🇨🇴', 'Cartagena', 'https://media.istockphoto.com/id/1165965255/es/foto/calle-en-ciudad-amurallada-en-cartagena-colombia.jpg?s=612x612&w=0&k=20&c=ZqFRZBPe01zoS9AQEA38yO-6UPChdmkluNNfySlzBDE=', 'Colorful streets, colonial buildings, and Caribbean charm await in this walled city full of culture and history.', 5),
  (12, 'Tayrona National Park', 'Colombia 🇨🇴', 'Santa Marta', 'https://media.istockphoto.com/id/600140144/es/foto/parque-nacional-natural-tayrona-en-colombia.jpg?s=612x612&w=0&k=20&c=-qTqVpt-dAgUkm5_GyAIPKeTP_nPZr6mQBm2EHWk1Ac=', 'Where jungle meets sea—this protected area has turquoise beaches, wild trails, and sacred indigenous lands.', 1),
  (16, 'Comuna 13', 'Colombia 🇨🇴', 'Medellín', 'https://media.istockphoto.com/id/1042287894/es/foto/comuna-13-en-medell%C3%ADn-colombia.jpg?s=612x612&w=0&k=20&c=dTpYyAnE338nuGNinQmRIpRgOEMvT01Pun_QJlAAYTQ=', 'A vibrant neighborhood transformed through art, music, and resilience, now a symbol of hope and urban renewal.', 2),
  (20, 'Caño Cristales', 'Colombia 🇨🇴', 'Meta', 'https://media.istockphoto.com/id/1217467705/es/foto/crystal-river-colombia.jpg?s=612x612&w=0&k=20&c=c7NDZf8VA-w2uWiTCaMSGlO1If-cBcedbbQrlZHJHIY=', 'Known as the "Liquid Rainbow," this river is famous for its vibrant colors caused by unique aquatic plants.', 5);