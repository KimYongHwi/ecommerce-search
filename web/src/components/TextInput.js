import React from 'react';

function TextInput({ value, setValue }) {
  return (
    <input
      type='search'
      id='search'
      class='block w-full p-4 ps-10 text-sm text-gray-900 border border-gray-300 rounded-lg'
      placeholder='Search'
      value={value}
      onChange={(e) => {
        setValue(e.target.value);
      }}
    />
  );
}

export default TextInput;
