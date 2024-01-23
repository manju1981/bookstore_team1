import Header from '../../components/Header'
import { DataGrid } from '@mui/x-data-grid'
import config from './ColumnConfig'
import { useState, useEffect } from 'react'

// const rows = [
//   { id: 1, authorName: 'Snow', bookName: 'Jon', price: 35, rating: 4 },
//   { id: 2, authorName: 'Lannister', bookName: 'Cersei', price: 42, rating: 4 },
//   { id: 3, authorName: 'Lannister', bookName: 'Jaime', price: 45, rating: 4 },
//   { id: 4, authorName: 'Stark', bookName: 'Arya', price: 16, rating: 4 },
//   { id: 5, authorName: 'Targaryen', bookName: 'Daenerys', price: 10, rating: 4 },
//   { id: 6, authorName: 'Melisandre', bookName: 'Bhagyashri', price: 150, rating: 4 },
//   { id: 7, authorName: 'Clifford', bookName: 'Ferrara', price: 44, rating: 4 },
//   { id: 8, authorName: 'Frances', bookName: 'Rossini', price: 36, rating: 4 },
//   { id: 9, authorName: 'Roxie', bookName: 'Harvey', price: 65, rating: 4 },
// ]

const gridStyle = {
  height: 'calc(100vh - 60px)',
  width: '100%',
}

function DataTable({ books }) {
  return (
    <div style={gridStyle} data-testid="list-table">
      <DataGrid
        rows={books}
        columns={config}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 5 },
          },
        }}
        sx={{ gap: 2 }}
        pageSizeOptions={[5, 10]}
        // checkboxSelection
      />
    </div>
  )
}

const BookListing = () => {
  const [books, setBooks] = useState([])

  useEffect(() => {
    fetch('http://localhost:8090/books')
      .then((response) => response.json())
      .then((response) => setBooks(response))
  }, [])

  return (
    <>
      <Header title="Team 1 Book Store" />
      <DataTable books={books} />
    </>
  )
}

export default BookListing
