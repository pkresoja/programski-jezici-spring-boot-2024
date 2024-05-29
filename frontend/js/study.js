const table = document.getElementById('study-programme-table')
const template = document.getElementById('study-programme')

fetch('http://localhost:8000/api/study-programme')
    .then(rsp => rsp.json())
    .then(data => {
        data.forEach(studyProgramme => {
            const copy = template.content.cloneNode(true)
            copy.querySelector('.id').innerText = studyProgramme.id
            copy.querySelector('.name').innerText = studyProgramme.name
            copy.querySelector('.created').innerText = formatDate(studyProgramme.createdAt)
            copy.querySelector('.updated').innerText = formatDate(studyProgramme.updatedAt)
            copy.querySelector('.edit').href = `./edit-study-programme.html?id=${studyProgramme.id}`
            copy.querySelector('.remove').addEventListener('click', () => {
                if (confirm(`Zelite obrisati smer ${studyProgramme.name}`)) {
                    fetch(`http://localhost:8000/api/study-programme/${studyProgramme.id}`, {
                        method: 'DELETE',
                    })
                        .then(rsp => {
                            if (rsp.status === 204) {
                                window.location.href = './study-programme.html'
                                return
                            }
                            alert(`Brisanje smera nije uspelo (HTTP ${rsp.status})`)
                        })
                }
            })
            table.appendChild(copy)
        })
    })