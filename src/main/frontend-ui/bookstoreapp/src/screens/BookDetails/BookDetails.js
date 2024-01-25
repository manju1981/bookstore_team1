import React, { useEffect, useState } from 'react'
import { Grid, Box, Rating, Button } from '@mui/material'
import Header from '../../components/Header'
import { Typography } from '@mui/material'
import { useParams } from 'react-router-dom'
import RemoveIcon from '@mui/icons-material/Remove'
import AddIcon from '@mui/icons-material/Add'
import { Unstable_NumberInput as BaseNumberInput } from '@mui/base/Unstable_NumberInput'
import {
  StyledInputRoot,
  StyledInput,
  StyledButton,
} from '../BookDetails/BookDetails.style'
import { styled } from '@mui/system'
import { AddShoppingCart } from '@mui/icons-material'
import { useNavigate } from 'react-router-dom'

const BookDetails = () => {
  const { id } = useParams()
  const [bookDetails, setBookDetails] = useState()
  const navigate = useNavigate()

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

  const navigateToCart = (params) => {
    console.log('1234', navigate)
    navigate(`/cart`)
  }

  const BookDetailsContainer = styled('div')({
    padding: '20px',
    maxWidth: '1500px',
    margin: 'auto',
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
        <Grid container spacing={3} rowGap={3}>
          <Grid item xs={12} md={4}>
            <Box
              component="img"
              sx={{ width: 300 }}
              alt={bookDetails.title}
              src={bookDetails.image_url}
            />
          </Grid>
          <Grid item xs={12} md={8} textAlign="left">
            <Typography variant="h5" data-testid="book-title">
              <p>{`${bookDetails.title}`}</p>
            </Typography>
            <Typography variant="h6" data-testid="book-author">
              <p>
                {'Author:'} {`${bookDetails.author}`}
              </p>
            </Typography>
            <Typography variant="subtitle1" data-testid="book-description">
              <p>
                {'Description:'} {`${bookDetails.description}`}
              </p>
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
            <Grid container spacing={3} marginTop={7}>
              <Grid item xs={12}>
                <NumberInput
                  aria-label="Quantity Input"
                  min={0}
                  max={99}
                  defaultValue="0"
                  sx={{ display: 'flex', justifyContent: 'flex-start' }}
                />
              </Grid>
              <Grid item xs={12}>
                <Button
                  variant="contained"
                  onClick={navigateToCart}
                  startIcon={<AddShoppingCart />}
                >
                  Add to Cart
                </Button>
              </Grid>
            </Grid>
          </Grid>
        </Grid>
      </BookDetailsContainer>
    </>
  )
}

export default BookDetails
