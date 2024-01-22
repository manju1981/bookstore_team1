import { render, screen } from '@testing-library/react'
import BookListing from './BookListing'

test('renders header', () => {
  render(<BookListing />)
  const header = screen.getByTestId('header-bar')
  const headerTitle = screen.getByTestId('header-bar-title')
  expect(header).toBeInTheDocument()
  expect(headerTitle).toHaveTextContent('Team 1 Book Store')
})

test('it should show table header and 5 rows of books in the list', () => {
  render(<BookListing />)
  const table = screen.getByTestId('list-table')
  expect(table).toBeInTheDocument()
  const book = screen.getByText('Book')
  expect(book).toBeInTheDocument()
  const author = screen.getByText('Author')
  expect(author).toBeInTheDocument()
  const price = screen.getByText('Price')
  expect(price).toBeInTheDocument()

  // const rating = screen.getByText('Rating');
  // expect(rating).toBeInTheDocument();
  // console.log("Rating--->",rating)
  const rowList = screen.getAllByRole('row')
  expect(rowList.length).toBe(4)
})
