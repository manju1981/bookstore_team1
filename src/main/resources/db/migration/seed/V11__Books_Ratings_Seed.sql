UPDATE BOOKS SET RATINGS = 3.4 WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'Clean Code' AND RATINGS = 0);
UPDATE BOOKS SET RATINGS = 2.9 WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'Introduction to Algorithms' AND RATINGS = 0);
UPDATE BOOKS SET RATINGS = 4.3 WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'Structure and Interpretation of Computer Programs (SICP)' AND RATINGS = 0);
UPDATE BOOKS SET RATINGS = 4.8 WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'The Clean Coder' AND RATINGS = 0);
UPDATE BOOKS SET RATINGS = 3.2 WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'Code Complete' AND RATINGS = 0);
UPDATE BOOKS SET RATINGS = 3.0 WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'Design Patterns' AND RATINGS = 0);
UPDATE BOOKS SET RATINGS = 2.0 WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'The Pragmatic Programmer' AND RATINGS = 0);