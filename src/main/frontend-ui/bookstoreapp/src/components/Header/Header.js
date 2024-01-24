import React, { useMemo } from 'react'

import AppBar from '@mui/material/AppBar'
import Box from '@mui/material/Box'
import Toolbar from '@mui/material/Toolbar'
import Typography from '@mui/material/Typography'
import MenuIcon from '@mui/icons-material/Menu'
import SearchIcon from '@mui/icons-material/Search'
import logo from '../../assets/book.png'
import { debounce } from 'lodash'

import { Search, StyledInputBase, SearchIconWrapper } from './Header.style'

export default function SearchAppBar({ title, onSearch }) {
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
        </Toolbar>
      </AppBar>
    </Box>
  )
}
