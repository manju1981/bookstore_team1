import Header from '../../components/Header'
import { DataGrid } from '@mui/x-data-grid'
import config from './ColumnConfig'
import { useState, useEffect } from 'react'

const gridStyle = {
  height: 'calc(100vh - 60px)',
  width: '100%',
}

function DataTable({ books }) {
  const [, setPage] = useState([])
  const [, setPageSize] = useState()

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
        onPaginationModelChange={(paginationData) => {
          setPage(paginationData.page)
          setPageSize(paginationData.pageSize)
        }}
      />
    </div>
  )
}

const BookListing = () => {
  const [filteredBooks, setFilteredBooks] = useState([])
  useEffect(() => {
    fetch('http://localhost:8090/books')
      .then((response) => response.json())
      .then((response) => {
        setFilteredBooks(response)
      })
  }, [])

  const handleSearch = (searchValue) => {
    fetch(`http://localhost:8090/books?search=${searchValue}`)
      .then((response) => response.json())
      .then((response) => {
        const filteredBooks = response.filter(
          (item) =>
            item.title.toLowerCase().includes(searchValue.toLowerCase()) ||
            item.author.toLowerCase().includes(searchValue.toLowerCase())
        )
        setFilteredBooks(filteredBooks)
      })
  }

  return (
    <>
      <Header title="Team 1 Book Store" onSearch={handleSearch} />
      <DataTable books={filteredBooks} />
    </>
  )
}

export default BookListing
