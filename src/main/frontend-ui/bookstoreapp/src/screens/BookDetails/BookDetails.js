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
import { styled } from '@mui/system'

const BookDetails = () => {
  const { id } = useParams()
  const [bookDetails, setBookDetails] = useState()

  const BookDetailsContainer = styled('div')({
    padding: '20px',
    maxWidth: '2000px',
    margin: 'auto',
  })

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
      <BookDetailsContainer>
        <Grid container>
          <Grid item xs={12} md={4}>
            <Box
              component="img"
              sx={{ width: 300 }}
              alt={bookDetails.title}
              src={BookImage}
            />
          </Grid>
          <Grid item xs={12} md={8} textAlign="left">
            <Typography variant="h5" data-testid="book-title">
              <p>{`${bookDetails.title}`}</p>
            </Typography>
            <Typography variant="h6" data-testid="book-author">
              <p>{`${bookDetails.author}`}</p>
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
                alignItems: 'start',
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
            <Grid
              container
              spacing={5}
              paddingTop={7}
              rowGap={2}
              sx={{ display: 'flex', justifyContent: 'flex-start' }}
            >
              <Grid item xs={12} display="flex" justifyContent="flex-start">
                <NumberInput aria-label="Quantity" max={20} defaultValue={0} />
              </Grid>
              <Grid item xs={12}>
                <Stack direction="row" spacing={2}>
                  <Button
                    variant="contained"
                    size="large"
                    startIcon={<ShoppingCartIcon />}
                  >
                    Add to Cart
                  </Button>
                </Stack>
              </Grid>
            </Grid>
          </Grid>
        </Grid>
      </BookDetailsContainer>
    </>
  )
}

export default BookDetails
