import React, { useMemo } from 'react'

import AppBar from '@mui/material/AppBar'
import Box from '@mui/material/Box'
import Toolbar from '@mui/material/Toolbar'
import Typography from '@mui/material/Typography'
import MenuIcon from '@mui/icons-material/Menu'
import SearchIcon from '@mui/icons-material/Search'
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart'
import logo from '../../assets/book.png'
import { debounce } from 'lodash'
import { useNavigate, useLocation } from 'react-router-dom'

import { Search, StyledInputBase, SearchIconWrapper } from './Header.style'

export default function SearchAppBar({ title, onSearch, showSearchBar = false }) {
  const debouncedSearch = useMemo(
    () =>
      debounce((value) => {
        onSearch(value)
      }, 500),
    [onSearch]
  )

  const onChange = (event) => {
    const { value } = event.target
    debouncedSearch(value)
  }

  const navigate = useNavigate()
  const location = useLocation()

  const isBookDetailsPage = location.pathname.includes(`/book/${''}`)

  const navigateToCart = (params) => {
    navigate(`/cart`)
  }

  return (
    <Box sx={{ flexGrow: 1 }} data-testid="header-bar">
      <AppBar position="static">
        <Toolbar>
          <MenuIcon />
          <Box
            component="img"
            sx={{
              height: 50,
              width: 50,
              ml: 2,
            }}
            alt="The house from the offer."
            src={logo}
          />

          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ flexGrow: 1, display: { xs: 'none', sm: 'block' } }}
            data-testid="header-bar-title"
          >
            {title}
          </Typography>
          {showSearchBar && (
            <Search>
              <SearchIconWrapper>
                <SearchIcon />
              </SearchIconWrapper>
              <StyledInputBase
                placeholder="Search…"
                inputProps={{ 'aria-label': 'search' }}
                onChange={onChange}
              />
            </Search>
          )}
          <ShoppingCartIcon sx={{ cursor: 'pointer', ml: 2 }} onClick={navigateToCart} />
        </Toolbar>
      </AppBar>
    </Box>
  )
}
