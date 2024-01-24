import Header from '../../components/Header'
import DataTable from './DataTable'
import { useState } from 'react'

const BookListing = () => {
  const [filteredBooks, setFilteredBooks] = useState([])

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
