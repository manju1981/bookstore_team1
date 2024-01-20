import { useEffect, useState } from "react"
import Button from '@mui/material/Button';


const ComponentA = ()=>{
    const [books, setBooks] = useState([])

    useEffect(()=>{
        fetch("http://127.0.0.1:8090/books")
            .then(response=>response.json())
            .then(response=>setBooks(response));
    },[])

    return (
        <>
          {books.map(book=>(
            <p className="row" key={book.id}>
                {`${book.title} ${book.author}`}
                <Button variant="contained">Hello world</Button>
            </p>
          ))}
        </>
    )
}

export default ComponentA