import React, { useEffect, useState } from 'react'
import { Grid, Box, Rating, Button, Stack } from '@mui/material'
import Header from '../../components/Header'
import { Typography } from '@mui/material'
import BookImage from '../../assets/book-image.png'
import { useParams } from 'react-router-dom'
import { Unstable_NumberInput as BaseNumberInput } from '@mui/base/Unstable_NumberInput'
import {
  StyledInputRoot,
  StyledInput,
  StyledButton,
} from '../BookDetails/BookDetails.style'
import RemoveIcon from '@mui/icons-material/Remove'
import AddIcon from '@mui/icons-material/Add'
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart'

const BookDetails = () => {
  const { id } = useParams()
  const [bookDetails, setBookDetails] = useState()

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

  useEffect(() => {
    const fetcher = () => {
      fetch(`http://localhost:8090/book/${id}`)
        .then((response) => response.json())
        .then((data) => {
          setBookDetails(data)
        })
    }
    fetcher()
  }, [id])

  if (!bookDetails) return null

  return (
    <>
      <Header title="Book Details" data-testid="header-bar" />
      <Grid container paddingLeft={10} paddingTop={10}>
        <Grid item xs={4} maxWidth={20}>
          <Box
            component="img"
            sx={{ width: 300 }}
            alt={bookDetails.title}
            src={BookImage}
          />
        </Grid>
        <Grid item xs={8} textAlign="left" paddingLeft={10}>
          <Typography variant="h5" data-testid="book-title">
            <p>{`${bookDetails.title}`}</p>
          </Typography>
          <Typography variant="subtitle1" data-testid="book-description">
            <p>{`${bookDetails.description}`}</p>
          </Typography>
          <Typography variant="h6" data-testid="book-price">
            <p>
              {'MRP: ₹'} {`${bookDetails.price}`}
            </p>
          </Typography>
          <Box
            sx={{
              width: 200,
              display: 'flex',
              alignItems: 'center',
            }}
          >
            <Rating
              name="text-feedback"
              readOnly
              value={bookDetails.ratings}
              data-testid="book-rating"
              precision={0.5}
            />
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
                <Button variant="contained" startIcon={<ShoppingCartIcon />}>
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
