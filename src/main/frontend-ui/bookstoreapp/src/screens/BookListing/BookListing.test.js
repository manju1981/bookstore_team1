import { render, screen, within } from '@testing-library/react';
import BookListing from './BookListing';
import DataTable from './DataTable';

const booksData = [
  { id: 1, author: 'Snow', title: 'Jon', price: 35, ratings: 1 },
  { id: 2, author: 'Lannister', title: 'Cersei', price: 42, ratings: 4 },
  { id: 3, author: 'Lannister', title: 'Jaime', price: 45, ratings: 2 },
  { id: 4, author: 'Stark', title: 'Arya', price: 16, ratings: 3 },
  { id: 5, author: 'Targaryen', title: 'Daenerys', price: 10, ratings: 4 },
  { id: 6, author: 'Melisandre', title: 'Bhagyashri', price: 150, ratings: 1 },
  { id: 7, author: 'Clifford', title: 'Ferrara', price: 44, ratings: 4 },
  { id: 8, author: 'Frances', title: 'Rossini', price: 36, ratings: 4 },
  { id: 9, author: 'Roxie', title: 'Harvey', price: 65, ratings: 5 },
];


test('renders header', () => {
  render(<BookListing />)
  const header = screen.getByTestId('header-bar')
  const headerTitle = screen.getByTestId('header-bar-title')
  expect(header).toBeInTheDocument()
  expect(headerTitle).toHaveTextContent('Team 1 Book Store')
})

test('it should show table header and 5 rows of books in the list', () => {

  render(<DataTable books={booksData} />)
  const table = screen.getByTestId('list-table')
  expect(table).toBeInTheDocument()
  const book = screen.getByText('Book Title')
  expect(book).toBeInTheDocument()
  const author = screen.getByText('Author Name')
  expect(author).toBeInTheDocument()
  const price = screen.getByText('Price')
  expect(price).toBeInTheDocument()

  // const ratings = screen.getByText('ratings');
  // expect(ratings).toBeInTheDocument();
  // console.log("ratings--->",ratings)
  const rowList = screen.getAllByRole("row");
  expect(rowList.length).toBe(4);
});

// test('the value of ratings should not be below 1 or more than 5', () => {
//   render(<BookListing/>);
//   const table = screen.getAllByRole('rowgroup')[1];
//   const row = within(table).getAllByRole('row')[0];
//   const cell = within(row).getAllByRole('cell')[3];
//   console.log("cells", within(row).queryAllByRole('cell').length);
//   expect(cell).toBe(1);
//   // const cells = screen.getAllByRole('cell');
//   // console.log("ratingsRows,",ratingsColumn)
// });


