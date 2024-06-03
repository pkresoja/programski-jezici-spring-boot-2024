const id = params.get('id')

if (id == null || id == '')
    window.location.href = './index.html'

fetch('http://localhost:8000/api/student/' + id)
    .then(rsp => {
        if (rsp.status == 200)
            return rsp.json()

        alert('Student nije pronadjen')
        window.location.href = './index.html'
    })
    .then(student => {
        const studentBreadcrumb = document.getElementById('student')
        studentBreadcrumb.href = `./edit.html?id=${student.id}`
        studentBreadcrumb.innerText = `${student.name} ${student.surname} ${student.indeks}`

        const template = document.getElementById('course')
        const list = document.getElementById('courses')
        fetch('http://localhost:8000/api/course')
            .then(rsp => rsp.json())
            .then(courseList => {
                courseList.forEach(course => {
                    const copy = template.content.cloneNode(true)
                    copy.querySelector('.form-check-label').innerText = course.name

                    const courseSwitch = copy.querySelector('.form-check-input')
                    if (student.courses.map(c => c.id).includes(course.id)) {
                        console.log(course.id)
                        courseSwitch.checked = true
                    }

                    courseSwitch.addEventListener('change', () => {
                        fetch(`http://localhost:8000/api/student/course/${student.id}`, {
                            method: 'PUT',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify({
                                course: course,
                                active: courseSwitch.checked
                            })
                        }).then(rsp => rsp.text())
                    })
                    list.appendChild(copy)
                })
            })
    })