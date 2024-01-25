import { render, screen } from '@testing-library/react'
import BookCart from './BookCart'
import { BrowserRouter as Router } from 'react-router-dom'

test('renders cart items', async () => {
  render(
    <Router>
      <BookCart />
    </Router>
  )

  const cartItem = screen.getByTestId('cart-item')
  expect(cartItem).toBeInTheDocument()
})
