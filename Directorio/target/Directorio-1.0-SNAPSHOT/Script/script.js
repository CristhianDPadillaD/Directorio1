const myModal = document.getElementById('myModal')
const myInput = document.getElementById('myInput')

myModal.addEventListener('shown.bs.modal', () => {
  myInput.focus()
})

const myDeleteModal = document.getElementById('myDeleteModal');
const deleteButton = document.getElementById('deleteButton');

myDeleteModal.addEventListener('shown.bs.modal', () => {
  deleteButton.focus();
});