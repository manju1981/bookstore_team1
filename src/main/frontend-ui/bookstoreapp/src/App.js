import './App.css'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import BookListing from './screens/BookListing'
import BookDetails from './screens/BookDetails'

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="*" element={<Navigate to="/books" replace={true} />} />
          <Route path="/books" element={<BookListing />} />
          <Route path="/book/:id" element={<BookDetails />} />
        </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App
