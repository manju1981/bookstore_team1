import { render, screen } from '@testing-library/react';
import BookListing from './BookListing';

test('renders header', () => {
  render(<BookListing/>);
  const header = screen.getByTestId("header-bar");
  const headerTitle = screen.getByTestId('header-bar-title')
  expect(header).toBeInTheDocument();
  expect(headerTitle).toHaveTextContent('Team 1 Book Store')
});


