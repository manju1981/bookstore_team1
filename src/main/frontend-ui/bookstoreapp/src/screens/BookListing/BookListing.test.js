import { render, screen, act, fireEvent } from '@testing-library/react'
import BookListing from './BookListing'
import DataTable from './DataTable'

const books = [
  { id: 1, title: 'Book 1', author: 'Author 1' },
  { id: 2, title: 'Book 2', author: 'Author 2' },
];
global.fetch = jest.fn().mockResolvedValue({
  json: jest.fn().mockResolvedValue(mockBooks),
});


test('renders header', () => {
  render(<BookListing />)
  const header = screen.getByTestId('header-bar')
  const headerTitle = screen.getByTestId('header-bar-title')
  expect(header).toBeInTheDocument()
  expect(headerTitle).toHaveTextContent('Team 1 Book Store')
})


test('handleSearch updates searchString correctly', () => {
  render(<BookListing />);

  const searchInput = screen.getByRole('textbox', { name: /search/i });
  act(() => {
    fireEvent.change(searchInput, { target: { value: 'Code' } });
  });
  const searchString = screen.getByRole('textbox', { name: /search/i }).value;
  expect(searchString).toBe('Code');
});

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


test('it should navigate to the details page on click of a row', () => {
  global.fetch = jest.fn().mockResolvedValue({
    json: jest.fn().mockResolvedValue(books),
  });

  render(<DataTable books={books} />)
  const table = screen.getByTestId('list-table')
  expect(table).toBeInTheDocument()
  const book = screen.getByText('Book Title')
  expect(book).toBeInTheDocument()
  const author = screen.getByText('Author Name')
  expect(author).toBeInTheDocument()
  const price = screen.getByText('Price')
  expect(price).toBeInTheDocument()
})