<a name="readme-top"></a>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]

<br />
<div align="center">

<h3 align="center">Cross Project Flaky Test Prediction</h3>

  <p align="center">
    Test cases for a software system ensure that changes made to the code do not adversely affect existing functionality, so much so that when developers make changes to their code, they perform what is known as regression testing to detect whether the changes made introduce any bugs within the system. However, some tests might be flaky, that is, nondeterministic is assume both pass and fail behavior when they are run on the same code. Initially, flakiness was addressed by re-executing the test case several times and observing its behavior. This approach is time-consuming and computationally expensive, so to date it has been decided to use machine learning to predict it. Several studies have been conducted on different datasets, following ad hoc methodologies that aimed to verify more the feasibility of using machine learning than the real practical utility using an in-vitro approach. With this work we try to do the exact opposite, that is, to use an in-vivo approach to show the real practical utility of machine learning toward flakiness. Basically, the repo involves running a series of experiments using: burak filter, CTCA, TCA, TrAda; of which it saves the results and takes care of plotting them. The tool is exclusively cross-project based.
    <br />
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->

## Getting Started

### Prerequisites

- Python 3.10+

### Installation

1. Create a virtual environment (optional but recommended)
   ```sh
   python -m venv env
   ```
2. Enable virtual environment (optional)
   ```sh
   source env/bin/activate   # macOS/Linux
   .\env\Scripts\activate    # Windows
   ```
3. Install the required packages
   ```sh
   pip install -r requirements.txt
   ```
4. Run the file `cross_project_experiment.py` from the `Flakiness_ML_Experiments` directory passing the dataset path without final `.csv` as parameter
   ```sh
   python cross_project_experiment.py path/to/dataset
   ```

When the main is started, an interface similar to the following will appear:

[Screenshot](https://imgur.com/rfbtIsH)

At this point, to run all experiments, type `1`, or choose the specific experiments you wish to run and then `0` to finish. Eventually, a boxplot will be generated with the results of the various metrics for all the experiments run.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTRIBUTING -->

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->

## License

Distributed under the MIT License. See `LICENSE` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->

## Authors and contact

The authors are:

- Angelo Afeltra
- Alfonso Cannavale - a.cannavale7@studenti.unisa.it
- Francesco Pecorelli - fpecorelli@unisa.it
- Valeria Pontillo - valeria.pontillo@vub.be
- Fabio Palomba - fpalomba@unisa.it

The affiliations are as follows:

- Angelo Afeltra, Alfonso Cannavale, Fabiano Pecorelli, and Fabio Palomba are affiliated with the Software Engineering (SeSa) Lab at the University of Salerno, Fisciano, Italy.
- Valeria Pontillo is affiliated with the Software Languages (Soft) Lab at Vrije Universiteit Brussel, Belgium.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

[contributors-shield]: https://img.shields.io/github/contributors/alfcan/crossproject-flaky-test-prediction.svg?style=for-the-badge
[contributors-url]: https://github.com/alfcan/crossproject-flaky-test-prediction/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/alfcan/crossproject-flaky-test-prediction.svg?style=for-the-badge
[forks-url]: https://github.com/alfcan/crossproject-flaky-test-prediction/network/members
[stars-shield]: https://img.shields.io/github/stars/alfcan/crossproject-flaky-test-prediction.svg?style=for-the-badge
[stars-url]: https://github.com/alfcan/crossproject-flaky-test-prediction/stargazers
[issues-shield]: https://img.shields.io/github/issues/alfcan/crossproject-flaky-test-prediction.svg?style=for-the-badge
[issues-url]: https://github.com/alfcan/crossproject-flaky-test-prediction/issues
[license-shield]: https://img.shields.io/github/license/alfcan/crossproject-flaky-test-prediction.svg?style=for-the-badge
[license-url]: https://github.com/alfcan/crossproject-flaky-test-prediction/blob/main/LICENSE
