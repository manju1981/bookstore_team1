import * as React from 'react'
import { Box, Typography } from '@mui/material'
import { Grid } from '@mui/material'
import Button from '@mui/material/Button'
import Header from '../../components/Header'
import BookImage from '../../assets/book-image.png'
import Dropdown from '../../components/Dropdown/Dropdown'

const BookCart = () => {
  const books = [
    {
      id: 8,
      title: 'Clean Code',
      author: 'Robert C. Martin',
      description:
        'Clean Code by Robert C. Martin guides developers to write elegant and maintainable code. Emphasizing readability and functionality, it promotes best practices for improved software design and collaboration.',
      ratings: 3,
      price: 100,
    },
    {
      id: 9,
      title: 'Introduction to Algorithms',
      author: 'Thomas H. Cormen',
      description:
        'Introduction to Algorithms by Thomas H. Cormen equips readers with essential algorithmic knowledge. This comprehensive guide is crucial for efficient problem-solving in computer science and practical software development.',
      ratings: 4,
      price: 100,
    },
  ]

  function CartItems({ books }) {
    return (
      <>
        {books.map((book) => (
          <Box
            sx={{
              display: 'flex',
              height: '200px',
              width: '100%',
              mt: 5,
            }}
            key={book.id}
          >
            <Box
              component="img"
              sx={{ width: 120, ml: 2 }}
              alt={book.title}
              src={BookImage}
            />
            <Grid item xs={4} textAlign="left" paddingLeft={8}>
              <Typography variant="h5" data-testid="book-title" mt={3}>
                {book.title}
              </Typography>
              <Typography variant="h10" data-testid="book-author" mt={1}>
                {book.author}
              </Typography>
              <Typography
                variant="body2"
                data-testid="book-title"
                sx={{ width: '80%', mt: 3 }}
              >
                {book.description}
              </Typography>
            </Grid>
            <Typography
              variant="subtitle1"
              sx={{
                marginLeft: 'auto',
                fontWeight: 'bold',
                width: '200px',
                mt: 4,
                mr: 8,
              }}
            >
              Price: ₹{book.price}
            </Typography>
          </Box>
        ))}
      </>
    )
  }

  return (
    <Box data-testid="cart-item">
      <Header title="Book Details" showSearchBar={false} />
      <CartItems books={books} />
      <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
        <Dropdown />
        <Button
          variant="contained"
          sx={{ ml: 'auto', m: 2, fontWeight: 'bold', width: '200px' }}
        >
          Total: ₹200
        </Button>
      </Box>
    </Box>
  )
}
export default BookCart
