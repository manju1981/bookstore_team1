import { render, screen, waitFor, fireEvent, within } from '@testing-library/react'
import BookListing from './BookListing'
<<<<<<< HEAD
import DataTable from './DataTable'

const books = [
  { id: 1, author: 'Snow', title: 'Jon', price: 35, ratings: 4 },
  { id: 2, author: 'Lannister', title: 'Cersei', price: 42, ratings: 4 },
  { id: 3, author: 'Lannister', title: 'Jaime', price: 45, ratings: 4 },
  { id: 4, author: 'Stark', title: 'Arya', price: 16, ratings: 4 },
  { id: 5, author: 'Targaryen', title: 'Daenerys', price: 10, ratings: 4 },
  { id: 6, author: 'Melisandre', title: 'Bhagyashri', price: 150, ratings: 4 },
  { id: 7, author: 'Clifford', title: 'Ferrara', price: 44, ratings: 4 },
  { id: 8, author: 'Frances', title: 'Rossini', price: 36, ratings: 4 },
  { id: 9, author: 'Roxie', title: 'Harvey', price: 65, ratings: 4 },
]
=======
>>>>>>> 7f63cc3 (Book Listing renders upon Search Value)

test('renders header', () => {
  render(<BookListing />)
  const header = screen.getByTestId('header-bar')
  const headerTitle = screen.getByTestId('header-bar-title')
  expect(header).toBeInTheDocument()
  expect(headerTitle).toHaveTextContent('Team 1 Book Store')
})

test('it should show table header and 5 rows of books in the list', () => {
  render(<DataTable books={books} />)
  const table = screen.getByTestId('list-table')
  expect(table).toBeInTheDocument()
  const book = screen.getByText('Book Title')
  expect(book).toBeInTheDocument()
  const author = screen.getByText('Author Name')
  expect(author).toBeInTheDocument()
  const price = screen.getByText('Price')
  expect(price).toBeInTheDocument()
  // const rating = screen.getByText('Rating');
  // expect(rating).toBeInTheDocument();
  // console.log("Rating--->",rating)
  // const tablerow = screen.getAllByRole('rowgroup')[1];
  // console.log('TableRow', tablerow);
})
