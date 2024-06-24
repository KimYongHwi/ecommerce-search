import React, { useState } from 'react';
import TextInput from './TextInput';
import axios from 'axios';

function Header({ setProducts, setTotal }) {
  const [formValue, setFormValue] = useState({ keyword: '' });

  function handleSubmit(e) {
    e.preventDefault();

    axios
      .get(`http://localhost:8080/products/search?keyword=${formValue.keyword}&page=0&size=10`)
      .then((res) => {
        setProducts(res.data.products);
        setTotal(res.data.total);
      })
      .catch((e) => {
        console.error(e.message);
      });
  }

  return (
    <div class='px-4 py-4 bg-white'>
      <div class='flex justify-center'>
        <form class='w-1/2' onSubmit={handleSubmit}>
          <label for='search' class='mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white'>
            Search
          </label>
          <div class='relative'>
            <div class='absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none'>
              <svg
                class='w-4 h-4 text-gray-500 dark:text-gray-400'
                aria-hidden='true'
                xmlns='http://www.w3.org/2000/svg'
                fill='none'
                viewBox='0 0 20 20'
              >
                <path
                  stroke='currentColor'
                  stroke-linecap='round'
                  stroke-linejoin='round'
                  stroke-width='2'
                  d='m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z'
                />
              </svg>
            </div>

            <TextInput
              value={formValue.keyword}
              setValue={(value) => {
                setFormValue((state) => ({ ...state, keyword: value }));
              }}
            />

            <button
              type='submit'
              class='text-white absolute end-2.5 bottom-2.5 bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800'
            >
              Search
            </button>
          </div>
        </form>
      </div>
    </div>
  );

  // return (
  //   <header class='py-4 shadow-sm bg-white'>
  //     <div class='container flex items-center justify-between'>
  //       <a href='index.html'>
  //         <img src='assets/images/logo.svg' alt='Logo' class='w-32' />
  //       </a>

  //       <div class='w-full max-w-xl relative flex'>
  //         <span class='absolute left-4 top-3 text-lg text-gray-400'>
  //           <i class='fa-solid fa-magnifying-glass'></i>
  //         </span>
  //         <input
  //           type='text'
  //           name='search'
  //           id='search'
  //           class='w-full border border-primary border-r-0 pl-12 py-3 pr-3 rounded-l-md focus:outline-none hidden md:flex'
  //           placeholder='search'
  //         />
  //         <button class='bg-primary border border-primary px-8 rounded-r-md hover:bg-transparent hover:text-primary transition hidden md:flex'>
  //           Search
  //         </button>
  //       </div>

  //       <div class='flex items-center space-x-4'>
  //         <a href='#' class='text-center text-gray-700 hover:text-primary transition relative'>
  //           <div class='text-2xl'>
  //             <i class='fa-regular fa-heart'></i>
  //           </div>
  //           <div class='text-xs leading-3'>Wishlist</div>
  //           <div class='absolute right-0 -top-1 w-5 h-5 rounded-full flex items-center justify-center bg-primary text-white text-xs'>
  //             8
  //           </div>
  //         </a>
  //         <a href='#' class='text-center text-gray-700 hover:text-primary transition relative'>
  //           <div class='text-2xl'>
  //             <i class='fa-solid fa-bag-shopping'></i>
  //           </div>
  //           <div class='text-xs leading-3'>Cart</div>
  //           <div class='absolute -right-3 -top-1 w-5 h-5 rounded-full flex items-center justify-center bg-primary text-white text-xs'>
  //             2
  //           </div>
  //         </a>
  //         <a href='#' class='text-center text-gray-700 hover:text-primary transition relative'>
  //           <div class='text-2xl'>
  //             <i class='fa-regular fa-user'></i>
  //           </div>
  //           <div class='text-xs leading-3'>Account</div>
  //         </a>
  //       </div>
  //     </div>
  //   </header>
  // );

  // return (
  //   <header class='py-4 shadow-sm bg-white'>
  //     <div class='container flex'>
  //       <div class='w-full max-w-xl relative flex items-center justify-between'>
  //         <input
  //           type='text'
  //           name='search'
  //           id='search'
  //           class='w-full border border-primary border-r-0 pl-12 py-3 pr-3 rounded-l-md focus:outline-none hidden md:flex'
  //           placeholder='search'
  //         />
  // <button class='bg-primary border border-primary px-8 rounded-r-md hover:bg-transparent hover:text-primary transition hidden md:flex'>
  //   Search
  // </button>
  //       </div>
  //     </div>
  //   </header>
  // );
}

export default Header;
