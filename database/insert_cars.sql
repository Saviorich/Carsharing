USE carsharing;

INSERT INTO cars (brand, model, color, mileage, gearbox, manufactured_year, engine_type, price_per_day, vin, plate, class, image_path)
    VALUES ('Chevrolet', 'Camaro', 'yellow', 1000, 'automatic', '2009', 'diesel', 55.9, '5GAEV23738J347034', '5912BM', 'muscle', 'images/camaro.jpg'),
           ('Toyota', 'Tundra', 'black', 5212, 'automatic', '2010', 'diesel', 49.9, '1GCEK14V02Z202167', '4951KK', 'SUV', 'images/tundra.jpg'),
           ('Citroen', 'C5', 'grey', 10122, 'manual', '2002', 'petrol', 19.9, '1C3CCCBB7FN508714', '1822TK', 'sedan', 'images/c5.jpg'),
           ('Skoda', 'Octavia', 'blue', 320120, 'manual', '2007', 'diesel', 23.9, '2G2WP522051332327', '9352BB', 'hatchback', 'images/octavia_hb.jpg'),
           ('Volkswagen', 'Polo', 'black', 1001, 'automatic', '2012', 'diesel', 32.5, '2G1WT58K679164754', '3832AM', 'sedan', 'images/polo_s.jpg'),
           ('Ford', 'Escort', 'grey', 8991, 'automatic', '2007', 'diesel', 15.1, 'WD8PD644265926385', '1241MK', 'wagon', 'images/escort_w.jpg'),
           ('Hummer', 'H3', 'yellow', 12021, 'automatic', '2005', 'diesel', 45.5, '3N1AB6AP5AL687934', '9942EK', 'SUV', 'images/h3.jpg'),
           ('Dodge', 'RAM', 'red', 50212, 'automatic', '2009', 'diesel', 49.9, '1ZWFT603615653640', '4263BK', 'pickup', 'images/ram.jpg'),
           ('Alfa Romeo', '156', 'grey', 71200, 'manual', '2006', 'diesel', 29.9, '2G1WB5E3XE1123801', '6482PP', 'sedan', 'images/156_s.jpg'),
           ('Mazda', 'RX8', 'blue', 32012, 'manual', '2010', 'diesel', 69.9, '1G11B5SL5EF181175', '9911MK', 'sport', 'images/rx8.jpg'),
           ('Lexus', 'IS300', 'grey', 19250, 'manual', '2005', 'petrol', 30, '3FA6P0HRXDR179846', '9241EM', 'wagon', 'images/is300_w.jpg'),
           ('Lada', 'Vesta', 'white', 89122, 'manual', '2012', 'petrol', 20, '1C4AJWBG7FL697611', '7721EK', 'sedan', 'images/vesta_s.jpg'),
           ('Audi', '80', 'red', 120120, 'manual', '1995', 'petrol', 19.9, '3N1BC1CP4BL488473', '0022CK', 'sedan', 'images/80_s.jpg');