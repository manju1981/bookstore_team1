import Header from '../../components/Header'
import DataTable from './DataTable'
import { useState } from 'react'

const BookListing = () => {
  const [searchString, setSearchString] = useState('')

  const handleSearch = (searchValue) => {
    setSearchString(searchValue)
  }

  return (
    <>
      <Header title="Team 1 Book Store" onSearch={handleSearch} />
      <DataTable searchString={searchString} />
    </>
  )
}

export default BookListing
