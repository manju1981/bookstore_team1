import Header from '../../components/Header'
import DataTable from './DataTable'
import { useState, useEffect } from 'react'

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
