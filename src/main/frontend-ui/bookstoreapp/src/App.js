import './App.css'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import BookListing from './screens/BookListing'
import BookDetails from './screens/BookDetails'

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<BookListing />} />
          <Route path="/book-details" element={<BookDetails />} />
        </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App
