import React, { useState } from 'react';
import { Grid, Box, Rating } from '@mui/material';
import { styled } from '@mui/material/styles';
import Header from '../../components/Header'
import { Typography } from "@mui/material";
import { Card, CardMedia, CardActionArea, Paper } from '@mui/material';
import StarIcon from '@mui/icons-material/Star';
import BookImage from '../../assets/book-image.png';





const BookListing = () => {

	const sampleBooks = [
		{
			id: 1,
			title: "Sample Book Title",
			description: "This is a sample book description. Lorem ipsum...",
			price: 19.99,
			imageUrl: { BookImage },
			rating: 3.5
		}];

	const [books, setBooks] = useState(sampleBooks)
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
	};

	return (
		<>
			<Header title="Book Details" />

			<Grid container paddingLeft={10} paddingTop={10}>
				<Grid container xs={4}>
					<Card>
						<CardActionArea>
							<CardMedia
								component="img"
								width={50}
								image={BookImage}
								alt="book-image"
							/>
						</CardActionArea>
					</Card>
				</Grid>
				<Grid container xs={8}>
					<Grid item xs={12}>
						<Typography variant="h5" data-testid="book-title">
							{books.map(book => (
								<p key={book.id}>
									{`${book.title}`}
								</p>
							))}
						</Typography>
					</Grid>
					<Grid item xs={12}>
						<Typography variant="subtitle1" data-testid="book-description">
							{books.map(book => (
								<p key={book.id}>
									{`${book.description}`}
								</p>
							))}
						</Typography>
					</Grid>
					<Grid item xs={12}>
						<Typography variant='h6' data-testid="book-price">
							{books.map(book => (
								<p key={book.id}>
									{"MRP: ₹"} {`${book.price}`}
								</p>
							))}
						</Typography>
					</Grid>
					<Grid item xs={12}>
						<Box
							sx={{
								width: 200,
								display: 'flex',
								alignItems: 'center',
							}}
						>
							{books.map(book => (
								<Rating
									key={book.id}
									name="text-feedback"
									value={book.rating}
									readOnly
									data-testid="book-rating"
									precision={0.5}
									emptyIcon={<StarIcon style={{ opacity: 0.55 }} fontSize="inherit" />}
								/>
							))}
							<Box sx={{ ml: 2 }}>{labels[books[0].rating]}</Box>
						</Box>
					</Grid>
					<Grid item xs={6}>
						Quantity
					</Grid>
					<Grid item xs={6}>
						Button
					</Grid>
				</Grid>
			</Grid>
		</>
	)
}

export default BookListing