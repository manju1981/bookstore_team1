UPDATE BOOKS SET DESCRIPTION = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit' WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'Clean Code' AND DESCRIPTION IS NULL);
UPDATE BOOKS SET DESCRIPTION = 'sed do eiusmod tempor incididunt ut labore et dolore magna aliqua' WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'Introduction to Algorithms' AND DESCRIPTION IS NULL);
UPDATE BOOKS SET DESCRIPTION = 'Ut enim ad minim veniam' WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'Structure and Interpretation of Computer Programs (SICP)' AND DESCRIPTION IS NULL);
UPDATE BOOKS SET DESCRIPTION = 'quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea' WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'The Clean Coder' AND DESCRIPTION IS NULL);
UPDATE BOOKS SET DESCRIPTION = 'Duis aute irure dolor in reprehenderit in voluptate' WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'Code Complete' AND DESCRIPTION IS NULL);
UPDATE BOOKS SET DESCRIPTION = 'velit esse cillum dolore eu fugiat nulla pariatur' WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'Design Patterns' AND DESCRIPTION IS NULL);
UPDATE BOOKS SET DESCRIPTION = 'Excepteur sint occaecat cupidatat non proident' WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'The Pragmatic Programmer' AND DESCRIPTION IS NULL);