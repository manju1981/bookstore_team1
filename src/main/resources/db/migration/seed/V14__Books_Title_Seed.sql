UPDATE BOOKS SET TITLE = 'Inside The Machine' WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'Clean Code' AND DESCRIPTION = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit');
UPDATE BOOKS SET TITLE = 'The self-taught programmer' WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'Introduction to Algorithms' AND DESCRIPTION = 'sed do eiusmod tempor incididunt ut labore et dolore magna aliqua');
UPDATE BOOKS SET TITLE = 'Head first Java' WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'Structure and Interpretation of Computer Programs (SICP)' AND DESCRIPTION = 'Ut enim ad minim veniam');
UPDATE BOOKS SET TITLE = 'Code - The hidden language of computer' WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'The Clean Coder' AND DESCRIPTION = 'quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea');
UPDATE BOOKS SET TITLE = 'Programming Pearl' WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'Code Complete' AND DESCRIPTION = 'Duis aute irure dolor in reprehenderit in voluptate');
UPDATE BOOKS SET TITLE = 'Algorithms to live by' WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'Design Patterns' AND DESCRIPTION = 'velit esse cillum dolore eu fugiat nulla pariatur');
UPDATE BOOKS SET TITLE = 'Peopleware' WHERE ID = (SELECT ID FROM BOOKS WHERE TITLE = 'The Pragmatic Programmer' AND DESCRIPTION = 'Excepteur sint occaecat cupidatat non proident');