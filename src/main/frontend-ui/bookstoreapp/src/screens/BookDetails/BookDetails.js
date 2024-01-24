import React, { useState, useEffect } from 'react'
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

const BookDetails = () => {
  const [book, setBook] = useState(null)
  useEffect(() => {
    const fetchBookDetails = async () => {
      try {
        const response = await fetch('YOUR_API_ENDPOINT')
        const data = await response.json()
        setBook(data)
      } catch (error) {
        console.error('Error fetching book details:', error)
      }
    }

    fetchBookDetails()
  }, [])

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
              <CardMedia component="img" image={book.image} alt="book-image" />
            </CardActionArea>
          </Card>
        </Grid>
        <Grid item xs={8} textAlign="left" paddingLeft={10}>
          <Typography variant="h5" data-testid="book-title">
            {book.title}
          </Typography>
          <Typography variant="h5" data-testid="book-description">
            {book.description}
          </Typography>
          <Typography variant="h6" data-testid="book-price">
            {'MRP: ₹'} {book.price}
          </Typography>
          <Box
            sx={{
              width: 200,
              display: 'flex',
              alignItems: 'center',
            }}
          >
            <Rating
              key={book.id}
              name="text-feedback"
              value={book.rating}
              readOnly
              data-testid="book-rating"
              precision={0.5}
              emptyIcon={<Star style={{ opacity: 0.55 }} fontSize="inherit" />}
            />
            <Box sx={{ ml: 2 }}>{labels[book.rating]}</Box>
          </Box>
          <Grid container spacing={2} paddingTop={5}>
            <Grid item xs={12}>
              <Typography variant="subtitle1" data-testid="select-quantity">
                Select Quantity:
              </Typography>
              <NumberInput aria-label="Quantity" min={1} max={20} paddingTop={10} />
            </Grid>
            <Grid item xs={12} paddingTop={5}>
              <Stack direction="row" spacing={2}>
                <Button variant="contained" startIcon={<AddShoppingCartIcon />}>
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

export default BookDetails
