import { render, screen } from '@testing-library/react';
import BookListing from './BookListing';

test('renders header', () => {
  render(<BookListing/>);
  const header = screen.getByTestId("header-bar");
  const headerTitle = screen.getByTestId('header-bar-title')
  expect(header).toBeInTheDocument();
  expect(headerTitle).toHaveTextContent('Team 1 Book Store')
});

test('it should show table header and 5 rows of books in the list', () => {
  render(<BookListing/>);
  const table = screen.getByTestId("list-table");
  expect(table).toBeInTheDocument();
  const rowHeader = screen.getByText('First name');
  expect(rowHeader).toBeInTheDocument();
  const rowList = screen.getAllByRole("row");
  expect(rowList.length).toBe(4);
});


