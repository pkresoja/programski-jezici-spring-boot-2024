const name = document.getElementById('name')
const surname = document.getElementById('surname')
const indeks = document.getElementById('indeks')

// Loading study programmes
fetch('http://localhost:8000/api/study-programme')
    .then(rsp => rsp.json())
    .then(studyData => {
        studyData.forEach(studyProgramme => {
            const option = document.createElement('option')
            option.value = studyProgramme.id
            option.text = studyProgramme.name
            study.appendChild(option)
        })

        document.getElementById('save').addEventListener('click', () => {
            if (name.value == null || name.value == '') {
                alert('Ime studenta ne sme biti prazno')
                return
            }

            if (surname.value == null || surname.value == '') {
                alert('Prezime studenta ne sme biti prazno')
                return
            }

            if (indeks.value == null || indeks.value == '') {
                alert('Indeks studenta ne sme biti prazan')
                return
            }

            fetch('http://localhost:8000/api/student', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: name.value,
                    surname: surname.value,
                    indeks: indeks.value,
                    studyProgrammeId: study.value
                })
            }).then(rsp => {
                if (rsp.ok) {
                    window.location.href = './index.html'
                    return
                }
                alert(`Dodavanje studenta nije uspelo (HTTP ${rsp.status})`)
            })
        })
    })