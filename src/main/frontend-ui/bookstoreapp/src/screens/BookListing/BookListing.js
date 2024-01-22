import { useEffect, useState } from "react"
import Header from '../../components/Header'
import DataTable from "./DataTable"



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
