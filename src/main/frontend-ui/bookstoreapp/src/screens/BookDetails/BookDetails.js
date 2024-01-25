import React, { useEffect, useState } from 'react'
import { Grid, Box, Rating } from '@mui/material'
import Header from '../../components/Header'
import { Typography } from '@mui/material'
import BookImage from '../../assets/book-image.png'
import { useParams } from 'react-router-dom'

const BookDetails = () => {
  const { id } = useParams()
  const [bookDetails, setBookDetails] = useState()

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
          <Grid container spacing={2} alignItems="center">
            <Grid item xs={6}>
              Quantity
            </Grid>
            <Grid item xs={6}>
              Button
            </Grid>
          </Grid>
        </Grid>
      </Grid>
    </>
  )
}

export default BookDetails
