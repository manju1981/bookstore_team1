import { render, screen } from '@testing-library/react';
import BookDetails from './BookDetails';

test('renders header', () => {
  render(<BookDetails/>);
  const header = screen.getByTestId("header-bar");
  const headerTitle = screen.getByTestId('header-bar-title')
  expect(header).toBeInTheDocument();
  expect(headerTitle).toHaveTextContent('Book Details')
});


test('render title of the Book', () => {
    render(<BookDetails/>);
    const bookTitle = screen.getByTestId("book-title");
    expect(bookTitle).toHaveTextContent('Sample Book Title')
});

test('render description of the Book', () => {
  render(<BookDetails/>);
  const bookTitle = screen.getByTestId("book-title");
  expect(bookTitle).toHaveTextContent('Sample Book Title')
});