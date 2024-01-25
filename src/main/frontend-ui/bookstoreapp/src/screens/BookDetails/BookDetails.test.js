import { render, screen, waitFor } from '@testing-library/react'
import BookDetails from './BookDetails'
import { BrowserRouter as Router } from 'react-router-dom'

jest.mock('node-fetch')

const mockBookDetails = {
  title: 'Sample Book Title',
  description: 'Sample Book Description',
}

test('renders header-bar', async () => {
  jest.spyOn(global, 'fetch').mockResolvedValue({
    json: jest.fn().mockResolvedValue(mockBookDetails),
  })

  render(
    <Router>
      <BookDetails />
    </Router>
  )

  await waitFor(() => {
    const header = screen.getByTestId('header-bar')
    expect(header).toBeInTheDocument()
  })
})

test('renders header-title', async () => {
  jest.spyOn(global, 'fetch').mockResolvedValue({
    json: jest.fn().mockResolvedValue(mockBookDetails),
  })

  render(
    <Router>
      <BookDetails />
    </Router>
  )

  await waitFor(() => {
    const headerTitle = screen.getByTestId('header-bar-title')
    expect(headerTitle).toHaveTextContent('Book Details')
  })
})

test('render title of the Book', async () => {
  jest.spyOn(global, 'fetch').mockResolvedValue({
    json: jest.fn().mockResolvedValue(mockBookDetails),
  })

  render(
    <Router>
      <BookDetails />
    </Router>
  )

  await waitFor(() => {
    const bookTitle = screen.getByTestId('book-title')
    expect(bookTitle).toHaveTextContent('Sample Book Title')
  })
})

test('render description of the Book', async () => {
  jest.spyOn(global, 'fetch').mockResolvedValue({
    json: jest.fn().mockResolvedValue(mockBookDetails),
  })

  render(
    <Router>
      <BookDetails />
    </Router>
  )

  await waitFor(() => {
    const bookDescription = screen.getByTestId('book-description')
    expect(bookDescription).toHaveTextContent('Sample Book Description')
  })
})

test('render price of the Book', async () => {
  jest.spyOn(global, 'fetch').mockResolvedValue({
    json: jest.fn().mockResolvedValue(mockBookDetails),
  })

  render(
    <Router>
      <BookDetails />
    </Router>
  )

  await waitFor(() => {
    const bookDescription = screen.getByTestId('book-price')
    expect(bookDescription).toHaveTextContent('MRP: ₹')
  })
})
