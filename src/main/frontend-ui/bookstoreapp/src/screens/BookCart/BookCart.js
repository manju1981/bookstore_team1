import * as React from 'react'
import { Typography } from '@mui/material'
import { Grid } from '@mui/material'
import Button from '@mui/material/Button'
import Header from '../../components/Header'
import BookImage from '../../assets/book-image.png'

const BookCart = () => {
  const height = 200
  const boxStyle = {
    display: 'flex',
    height: `${height}px`,
    width: '100%', // Set width to 90%
    margin: '5px auto 10px auto', // Center the box horizontally
    boxShadow: '0 0px 3px rgba(0, 0, 0, 0.2)', // Add shadow effect
    // borderRadius: '8px', // Add border-radius for a rounded corner effect
    overflow: 'hidden', // Hide overflow content if any
    // backgroundColor: 'red',
  }

  const imageStyle = {
    padding: '20px',
    width: '120px',
    objectFit: 'cover',
  }

  const checkoutBoxStyle = {
    display: 'flex',
    width: '100%', // Set width to 90%
    margin: '10px auto 10px auto', // Center the box horizontally
    // boxShadow: '0 0px 3px rgba(0, 0, 0, 0.2)', // Add shadow effect
    // overflow: 'hidden', // Hide overflow content if any
  }

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

  function CartItem({ key, title, author, description, price }) {
    return (
      <div style={boxStyle} key={key}>
        <img src={BookImage} alt="Custom" style={imageStyle} />
        <Grid item xs={4} textAlign="left" paddingLeft={8}>
          <Typography variant="h5" data-testid="book-title" mt={3}>
            {title}
          </Typography>
          <Typography variant="h10" data-testid="book-author" mt={1}>
            {author}
          </Typography>
          <Typography
            variant="body2"
            data-testid="book-title"
            mt={3}
            sx={{ width: '80%' }}
          >
            {description}
          </Typography>
        </Grid>
        <Typography
          variant="subtitle1"
          mt={4}
          mr={8}
          sx={{ marginLeft: 'auto', fontWeight: 'bold', width: '200px' }}
        >
          Price: ₹{price}
        </Typography>
      </div>
    )
  }

  return (
    <>
      <Header title="Book Details" showSearchBar={false} />
      {books.map((book) => (
        <CartItem
          key={book.id}
          author={book.author}
          title={book.title}
          description={book.description}
          price={book.price}
        />
      ))}
      <div style={checkoutBoxStyle}>
        <Grid item xs={4} textAlign="left" paddingLeft={8}></Grid>
        <div style={{ marginLeft: 'auto', fontWeight: 'bold', width: '250px' }}>
          {/* <Typography
            variant="subtitle1"
            mt={4}
            mr={8}
            sx={{ marginLeft: 'auto', width: '250px' }}
          >
            Total: ₹{'200'}
          </Typography> */}
          <Button variant="contained" sx={{ m: 2, fontWeight: 'bold', width: '200px' }}>
            Total: ₹200
          </Button>
        </div>
      </div>
    </>
  )
}

export default BookCart
