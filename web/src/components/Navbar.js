import React from 'react';

function Navbar() {
  return (
    <div class>
      <nav class='bg-gray-800'>
        <div class='container flex'>
          <div class='px-8 py-4 bg-primary md:flex items-center cursor-pointer relative group'>
            <span class='text-white'>
              <i class='fa-solid fa-bars'></i>
            </span>
            <span class='capitalize ml-2 text-white'>All Categories</span>

            <div class='absolute w-full left-0 top-full bg-white shadow-md py-3 divide-y divide-gray-300 divide-dashed opacity-0 group-hover:opacity-100 transition duration-300 invisible group-hover:visible'>
              <a href='#' class='flex items-center px-6 py-3 hover:bg-gray-100 transition'>
                <span class='ml-6 text-gray-600 text-sm'>Sofa</span>
              </a>
              <a href='#' class='flex items-center px-6 py-3 hover:bg-gray-100 transition'>
                <span class='ml-6 text-gray-600 text-sm'>Terarce</span>
              </a>
              <a href='#' class='flex items-center px-6 py-3 hover:bg-gray-100 transition'>
                <span class='ml-6 text-gray-600 text-sm'>Bed</span>
              </a>
              <a href='#' class='flex items-center px-6 py-3 hover:bg-gray-100 transition'>
                <span class='ml-6 text-gray-600 text-sm'>office</span>
              </a>
              <a href='#' class='flex items-center px-6 py-3 hover:bg-gray-100 transition'>
                <span class='ml-6 text-gray-600 text-sm'>Outdoor</span>
              </a>
              <a href='#' class='flex items-center px-6 py-3 hover:bg-gray-100 transition'>
                <span class='ml-6 text-gray-600 text-sm'>Mattress</span>
              </a>
            </div>
          </div>
        </div>
      </nav>
    </div>
  );
}

export default Navbar;
