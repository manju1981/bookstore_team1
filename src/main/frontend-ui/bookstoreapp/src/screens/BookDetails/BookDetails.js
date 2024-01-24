import React, { useState } from 'react'
import { Grid, Box, Rating } from '@mui/material'
import Header from '../../components/Header'
import { Typography } from '@mui/material'
import { Card, CardMedia, CardActionArea, Button, Stack } from '@mui/material'
import BookImage from '../../assets/book-image.png'
import { Unstable_NumberInput as BaseNumberInput } from '@mui/base/Unstable_NumberInput'
import { StyledInputRoot, StyledInput, StyledButton } from './BookDetails.style'
import Star from '@mui/icons-material/Star'
import RemoveIcon from '@mui/icons-material/Remove'
import AddIcon from '@mui/icons-material/Add'
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart'

const BookListing = () => {
  const sampleBooks = [
    {
      id: 1,
      title: 'Sample Book Title',
      description: 'This is a sample book description. Lorem ipsum...',
      price: 19.99,
      imageUrl: { BookImage },
      rating: 3.5,
    },
  ]

  const NumberInput = React.forwardRef(function CustomNumberInput(props, ref) {
    return (
      <BaseNumberInput
        slots={{
          root: StyledInputRoot,
          input: StyledInput,
          incrementButton: StyledButton,
          decrementButton: StyledButton,
        }}
        slotProps={{
          incrementButton: {
            children: <AddIcon fontSize="small" />,
            className: 'increment',
          },
          decrementButton: {
            children: <RemoveIcon fontSize="small" />,
          },
        }}
        {...props}
        ref={ref}
      />
    )
  })

  const [books] = useState(sampleBooks)
  const labels = {
    0.5: 'Useless',
    1: 'Useless+',
    1.5: 'Poor',
    2: 'Poor+',
    2.5: 'Ok',
    3: 'Ok+',
    3.5: 'Good',
    4: 'Good+',
    4.5: 'Excellent',
    5: 'Excellent+',
  }

  return (
    <>
      <Header title="Book Details" />

      <Grid container paddingLeft={10} paddingTop={10}>
        <Grid item xs={4} maxWidth={20}>
          <Card>
            <CardActionArea>
              <CardMedia component="img" image={BookImage} alt="book-image" />
            </CardActionArea>
          </Card>
        </Grid>
        <Grid item xs={8} textAlign="left" paddingLeft={10}>
          <Typography variant="h5" data-testid="book-title">
            {books.map((book) => (
              <p key={book.id}>{`${book.title}`}</p>
            ))}
          </Typography>
          <Typography variant="subtitle1" data-testid="book-description">
            {books.map((book) => (
              <p key={book.id}>{`${book.description}`}</p>
            ))}
          </Typography>
          <Typography variant="h6" data-testid="book-price">
            {books.map((book) => (
              <p key={book.id}>
                {'MRP: ₹'} {`${book.price}`}
              </p>
            ))}
          </Typography>
          <Box
            sx={{
              width: 200,
              display: 'flex',
              alignItems: 'center',
            }}
          >
            {books.map((book) => (
              <Rating
                key={book.id}
                name="text-feedback"
                value={book.rating}
                readOnly
                data-testid="book-rating"
                precision={0.5}
                emptyIcon={<Star style={{ opacity: 0.55 }} fontSize="inherit" />}
              />
            ))}
            <Box sx={{ ml: 2 }}>{labels[books[0].rating]}</Box>
          </Box>
          <Grid container spacing={2} alignItems="left" paddingTop={5}>
            <Grid item xs={4}>
              <NumberInput aria-label="Quantity" min={1} max={20} />
            </Grid>
            <Grid item xs={8}>
              <Stack direction="row" spacing={2}>
                <Button variant="outlined" startIcon={<AddShoppingCartIcon />}>
                  Add to Cart
                </Button>
              </Stack>
            </Grid>
          </Grid>
        </Grid>
      </Grid>
    </>
  )
}

export default BookListing
