const id = params.get('id')

if (id == null || id == '')
    window.location.href = './index.html'

const breadcrumb = document.getElementById('breadcrumb')
const sid = document.getElementById('id')
const name = document.getElementById('name')
const surname = document.getElementById('surname')
const indeks = document.getElementById('indeks')
const created = document.getElementById('created')
const updated = document.getElementById('updated')

fetch('http://localhost:8000/api/student/' + id)
    .then(rsp => {
        if (rsp.status == 200)
            return rsp.json()

        alert('Student nije pronadjen')
        window.location.href = './index.html'
    })
    .then(data => {
        breadcrumb.innerText = `${data.name} ${data.surname}`
        sid.value = data.id
        name.value = data.name
        surname.value = data.surname
        indeks.value = data.indeks
        created.value = formatDate(data.createdAt)
        updated.value = formatDate(data.updatedAt)

        document.getElementById('save').addEventListener('click', () => {
            fetch(`http://localhost:8000/api/student/${data.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: name.value,
                    surname: surname.value,
                    indeks: indeks.value,
                })
            })
                .then(rsp => {
                    if (rsp.ok) {
                        window.location.href = './index.html'
                        return
                    }
                    alert(`Izmena studenta nije uspela (HTTP ${rsp.status})`)
                })
        })
    })