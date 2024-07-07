import React from 'react';

function Breadcrumb() {
  return (
    <div class='container py-4 flex items-center gap-3'>
      <a href='/' class='text-primary text-base'>
        <p class='text-gray-600 font-medium'>Home</p>
      </a>
      <p class='text-gray-600 font-medium'>&#62;</p>
      <p class='text-gray-600 font-medium'>Shop</p>
    </div>
  );
}

export default Breadcrumb;
